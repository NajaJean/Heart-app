<script>
import { Line } from 'vue-chartjs'
import 'chartjs-plugin-streaming';
import 'chartjs-adapter-date-fns';
import MeasurementDataService from '../../services/MeasurementDataService';

var count = 0;

export default {
  fetchCount() {
    return count;
  },
  extends: Line,
  props:[],
  mounted () {
    this.renderChart({
      datasets: [{
        lastRecordedECG: [],
        data: [],
        label: 'ECG',
        pointHitRadius: 0,
        pointRadius: 0.5,
        tension: 0,
        backgroundColor:'#004346',
        borderColor: '#004346',
        fill: false,
      }]
    }, {responsive: true, 
          maintainAspectRatio: true,
          title:{
            display:false,
            text:'Old recorded ECG',
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
        yAxes: [{
            ticks: {
                min: -3000,
                max: 4000,
            }
        }],
        xAxes: [{
          reverse: true,
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
            refresh: 1000,
            onRefresh: function(chart) {
              chart.data.datasets.forEach(function(dataset) {
                MeasurementDataService.getOldECG("1")
                .then(response => {
                    const ecg = response.data[count%5000];
                    //const datepost = (ecg.datepost).substring(0, ecg.datepost.length - 6) + "-02:00";

                    if (dataset.lastRecordedECG != ecg && ecg) {
                      var ptime = new Date();
                      for (var i = 0; i < ecg.measurementvalue.length - 1; i++) {
                        ptime = new Date(ptime.getTime() + 7.8125)
                        dataset.data.push({
                        x: ptime,
                        y: ecg.measurementvalue[i]
                        })
                      }

                    chart.update();
                    dataset.lastRecordedECG = ecg; 
                  }     
                })
                .catch(e => {
                  console.log(e);
                });
                count++;
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
          frameRate: 20       // chart is drawn 30 times every second
      }
  }
}
</script>
