<script>
import { Bar } from 'vue-chartjs'

export default {
  extends: Bar,
  props:['chart'],
  mounted () {
      this.renderChart({
        labels: this.chart[0],
        datasets:[
          {
          label:'Light',
          data:this.chart[1],
          backgroundColor:'rgba(65,105,225, 0.6)',
          borderColor: 'rgba(65,105,225, 0.6)',
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
        },
        {
          label:'REM',
          data:this.chart[2],
          backgroundColor:'rgba(255, 59, 10, 0.6)',
          borderColor: 'rgba(255, 59, 10, 0.6)',
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
        },
        {
          label:'Deep',
          data:this.chart[3],
          backgroundColor:'rgba(148,0,211, 0.6)',
          borderColor: 'rgba(148,0,211, 0.6)',
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
        },],      
      }, {
        responsive:true,
		maintainAspectRatio: false,
        title:{
          display:true,
          text:'Sleep during the week',
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
		  displayColors: true
        }})
    },
}
</script>