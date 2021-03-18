<script>
import { Line } from 'vue-chartjs'

export default {
  extends: Line,
  props:['chart'],
  mounted () {
      this.renderChart({
        labels: this.chart[0],
        datasets:[
          {
          label:'Systolic',
                data: this.chart[1],
              backgroundColor:['rgba(255, 59, 10, 0.6)'
          ],
          borderColor: ['rgba(255, 59, 10, 0.6)'
          ],
          fill: false,
          tension: 0,
          scaleShowValues: true,
          scales: {
            xAxes: [{
            ticks: {
              autoSkip: false
            }
            }]
          }
            },{
          label:'Diastolic',
                data:this.chart[2],
              backgroundColor:['rgba(29, 179, 44, 0.6)'
          ],
          borderColor: ['rgba(29, 179, 44, 0.6)'
          ],
          fill: false,
          tension: 0,
          scaleShowValues: true,
          scales: {
            xAxes: [{
            ticks: {
              autoSkip: false
            }
            }]
          }
        }],
        
      }, {responsive: true, 
          maintainAspectRatio: false,
          title:{
            display:true,
            text:'Blood pressure during the week',
            fontSize:25
          },
          legend:{
            display:false,
            position:'right',
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
            enabled:true,
            displayColors: false
          },
          hover: {
            mode: 'point'
          }
        })
    },
}
</script>