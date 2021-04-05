<script>
import { Scatter } from 'vue-chartjs'
import 'chartjs-plugin-streaming';
import MeasurementDataService from "../../services/MeasurementDataService";

export default {
  extends: Scatter,
  props:['pauseX'],
  mounted () {
    this.renderChart({
      datasets: [{
        lastRecordedTime: new Date(),
        data: [],
        label: 'ECG',
        pointHitRadius: 0,
        pointRadius: 1,
        tension: 0,
        backgroundColor:'#004346',
        borderColor: '#004346',
        fill: false,
        //xAxisID: 'x-1',
      }]
    }, {responsive: true, 
          maintainAspectRatio: true,
          title:{
            display:false,
            text:'Live ECG',
            fontSize:25
          },
          legend:{
            display:true,
            position:'bottom',
            labels:{
              fontColor:'#000'
            }
          },
          layout:{
            padding:{
              left:50,
              right:0,
              bottom:0,
              top:0
            }
          },
          tooltips:{
            enabled: false,
          },
          
      scales: {
        xAxes: [{
          ticks: {
            autoSkip: true,
            maxTicksLimit: 15
          },
          time: {
            format: undefined
          },
          type: 'realtime',
          /*streaming: {
            
          },*/
          realtime: {
            //pauseX: this.pauseX,
            
            duration: 10000,
            refresh: 500,
            pause: this.pauseX,
            onRefresh: function(chart) {
              chart.data.datasets.forEach(function(dataset) {

                MeasurementDataService.get125LatestECG("010101-1234")
                .then(response => {
                  
                  //console.log(dataset.lastRecordedTime +'<'+ new Date(response.data[0].datepost));
                  if (dataset.lastRecordedTime <= new Date(response.data[0].datepost)) {
                    var recentRec = dataset.lastRecordedTime;
                    //console.log('post');

                    response.data.map( function(m) {
                      if (recentRec <= new Date(m.datepost)) {
                        dataset.data.push({
                          x: new Date(m.datepost),//       Date.now(), //Date(latestECG.datepost),
                          y: m.measurementvalue
                        })
                      }
                    }
                    );
                    chart.update();
                    dataset.lastRecordedTime = new Date(response.data[response.data.length-1].datepost);
                  }     
                })
                .catch(e => {
                  console.log(e);
                });
              });
            },
            delay: 1000
          }
        }]
      }
    });
  },
  plugins: {
      streaming: {            // per-chart option
          frameRate: 30       // chart is drawn 30 times every second
      }
  }
}
</script>