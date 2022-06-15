<script>
//import { Line } from 'vue-chartjs'
import 'chartjs-plugin-streaming';
import MockedData from "../Charts/MockedData"

var count = 0;

export default {
  //extends: Line,
  props:['mocked'],
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
            text:'Recorded ECG',
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
                min: -2000,
                max: 2000,
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
                var yval = MockedData.getMockedData(count%50);
                
                var ptime = new Date();
                for (var i = 0; i < yval.length - 1; i++) {
                  ptime = new Date(ptime.getTime() + 8)

                  dataset.data.push({
                    x: ptime,
                    y: yval[i]
                  })
                }
                chart.update();
                count = count +1;                 
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