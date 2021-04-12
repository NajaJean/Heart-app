<script>
import { Line } from 'vue-chartjs'
import 'chartjs-plugin-streaming';
import MeasurementDataService from "../../services/MeasurementDataService";

export default {
  extends: Line,
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

                MeasurementDataService.getLatestECG("010101-1234")
                .then(response => {
                  const ecg = response.data[0];
                  console.log(dataset.lastRecordedTime+" <="+ new Date(ecg.datepost))
                  if (dataset.lastRecordedTime <= new Date(ecg.datepost)) {
                    
                      for (var i = 0; i < ecg.measurementvalue.length-2; i = i + 2) {
                        /*var byteArray = new byteArray(2);
                        byteArray[0] = ecg.measurementvalue[i];
                        byteArray[1] = ecg.measurementvalue[i + 1];
                        
                        var value = 0;
                        for (var i = byteArray.length - 1; i >= 0; i--) {
                            value = (value * 256) + byteArray[i];
                          }*/
                          var value = ecg.measurementvalue[i+1] << 8 | ecg.measurementvalue[i]

                          console.log(value)
                            
                        //var ecgValue = ecg.measurementvalue[i] + ecg.measurementvalue[i + 1];
                      var spreadtime = ((i*1000)/ecg.measurementvalue.length);

                      dataset.data.push({
                        x: new Date((new Date(ecg.datepost)).getTime()+spreadtime),
                        y: value
                      })

                    }

                    chart.update();
                    dataset.lastRecordedTime = new Date(ecg.datepost);
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