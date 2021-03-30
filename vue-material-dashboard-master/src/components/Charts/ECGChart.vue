<script>
import MeasurementDataService from "../../services/MeasurementDataService";
import { Line } from 'vue-chartjs'
//import * as ChartAnnotation from 'chartjs-plugin-annotation';

export default { 
  extends: Line,
  props:['chart'],
  mounted () {
      this.renderChart({
        //labels: this.chart[0],
        datasets:[
          {
          label:'ECG',
          data: [],
          backgroundColor:'#004346',
          borderColor: '#004346',
          fill: false,
          tension: 0,
          pointHitRadius: 0,
          pointRadius: 0,
          scaleShowValues: true,
          scales: {
            xAxes: [{
            ticks: {
              autoSkip: false
            }
            }]
          },
        }],  
      }, {responsive: true, 
          maintainAspectRatio: false,
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
          scales:{
            xAxes: [{
              realtime: {
                onRefresh: function(chart) {
                  this.datasets.data.forEach(function(dataset) {
                    console.log("heeej1");
                    const latestECG = this.getLatestECG();
                    console.log("heeej");
                    dataset.data.push({
                      x: latestECG.datepost,
                      y: latestECG.measurementvalue
                    });
                  });
                }
              }
            }]
          }
        })
    },
    methods: {
      getLatestECG() {
        MeasurementDataService.getLatestECG()
        var x = {};
        MeasurementDataService.getLatestECG("010101-1234")
        .then(response => {
          response.data.map(m => x = m);
        })
        .catch(e => {
          console.log(e);
        });
        return x;
      }
    },
}
</script>