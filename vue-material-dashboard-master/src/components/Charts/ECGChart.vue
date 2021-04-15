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
            format: undefined,
            displayFormats: {
                millisecond: 'H:mm:ss.SSS',
                second: 'H:mm:ss',
                minute: 'H:mm',
                hour: 'H'
            }
          },
          type: 'realtime',
          realtime: {
            duration: 5000,
            refresh: 500,
            pause: this.pauseX,
            onRefresh: function(chart) {
              chart.data.datasets.forEach(function(dataset) {

                MeasurementDataService.getLatestECG("1")
                .then(response => {
                    const ecg = response.data[0];
                  //  console.log(ecg);
                    const datepost = (ecg.datepost).substring(0, ecg.datepost.length - 6) + "-02:00";
                    //console.log(new Date(new Date(ecg.datepost).getTime() + 8640000/24*2));
                    //console.log(new Date(datepost));

                    if (dataset.lastRecordedTime <= new Date(datepost)) {

                      for (var i = 0; i < ecg.measurementvalue.length-1; i++) {
                        
                        var spreadtime = ((i*1000)/(ecg.measurementvalue.length-1));
                   //       console.log(ecg.measurementvalue[i])
                        dataset.data.push({
                          x: new Date((new Date(datepost)).getTime()+spreadtime),
                          y: ecg.measurementvalue[i]
                        })

                    }

                    chart.update();
                    dataset.lastRecordedTime = new Date(datepost);
                  }     
                })
                .catch(e => {
                  console.log(e);
                });
              });
            },
            delay: 2000
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