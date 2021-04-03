<script>
import { Line } from 'vue-chartjs'
import 'chartjs-plugin-streaming';
import MeasurementDataService from "../../services/MeasurementDataService";

export default {
  extends: Line,
  mounted () {
    this.renderChart({
      datasets: [{
        lastRecordedTime: new Date(),
        data: [],
        label: 'ECG',
        pointHitRadius: 0,
        pointRadius: 0,
        tension: 0,
        backgroundColor:'#004346',
        borderColor: '#004346',
        fill: false,
      }]
    }, {responsive: true, 
          maintainAspectRatio: true,
          title:{
            display:false,
            text:'ECG during the week',
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
          time: {
            format: 'false'
          },
          ticks: {
            autoSkip: true,
            maxTicksLimit: 15
          },
          type: 'realtime',
          realtime: {
            refresh: 50,
            onRefresh: function(chart) {
              chart.data.datasets.forEach(function(dataset) {
                
                MeasurementDataService.get125LatestECG("010101-1234")
                .then(response => {
                  
                  //console.log(dataset.lastRecordedTime +'<'+ new Date(response.data[0].datepost));
                  if (dataset.lastRecordedTime <= new Date(response.data[0].datepost)) {
                    //console.log('post');
                    response.data.map(m => 
                      dataset.data.push({
                        x: Date.now(),//new Date(m.datepost),//       Date.now(), //Date(latestECG.datepost),
                        y: m.measurementvalue
                      })
                    );
                    dataset.lastRecordedTime = new Date(response.data[response.data.length-1].datepost);
                  }
                  


                  //chart.data.datasets.ecg = response.data[0].measurementvalue;
                  //console.log(chart.data.datasets.ecg);
                })
                .catch(e => {
                  console.log(e);
                });

                
              });
            },
            delay: 0
          }
        }]
      }
    });
  },
}
</script>