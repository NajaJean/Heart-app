<script>
import { Line } from 'vue-chartjs'
import 'chartjs-plugin-streaming';
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
        lastRecordedTime: new Date("2021-01-01T00:00:32.689+00:00"),
        data: [],
        label: 'ECG',
        pointHitRadius: 0,
        pointRadius: 1,
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

                    var ptime = new Date();
                    if (dataset.lastRecordedTime <= ptime) {
                        for (var i = 0; i < ecg.measurementvalue.length - 1; i++) {
                          ptime = new Date(ptime.getTime() + 7.8125)
                          dataset.data.push({
                          x: ptime,
                          y: ecg.measurementvalue[i]
                          })
                        }

                    chart.update();
                    dataset.lastRecordedTime = ptime; 
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
