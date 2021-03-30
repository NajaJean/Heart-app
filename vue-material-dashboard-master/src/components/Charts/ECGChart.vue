<script>
import { Line } from 'vue-chartjs'
import 'chartjs-plugin-streaming';
import MeasurementDataService from "../../services/MeasurementDataService";

export default {
  extends: Line,
  mounted () {
    this.renderChart({
      datasets: [{
        ecg: 0,
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
          type: 'realtime',
          realtime: {
            refresh: 1,
            onRefresh: function(chart) {
              chart.data.datasets.forEach(function(dataset) {
                
                MeasurementDataService.getLatestECG("010101-1234")
                .then(response => {
                  chart.data.datasets.ecg = response.data[0].measurementvalue;
                  console.log(chart.data.datasets.ecg);
                })
                .catch(e => {
                  console.log(e);
                });

                dataset.data.push({
                  x: Date.now(), //Date(latestECG.datepost),
                  y: chart.data.datasets.ecg
                });
              });
            },
            delay: 2000
          }
        }]
      }
    });
  },
}
</script>