<script>
import { Bar } from 'vue-chartjs'

export default {
  extends: Bar,
  data: () => ({
    chartdata: {
      labels:['1. feb','2. feb','3. feb','4. feb','5. feb','6. feb','7. feb','8. feb','9. feb','10. feb'],
        datasets:[{
          label:'Steps',
          data:[
            17594,
            1045,
            13060,
            6519,
            5162,
            10072,
            8272,
            10172,
            1945,
            2145
          ],
          backgroundColor:[
            'rgba(29, 179, 44, 0.6)',
            'rgba(255, 59, 10, 0.6)',
            'rgba(29, 179, 44, 0.6)',
            'rgba(255, 255, 10, 0.6)',
            'rgba(255, 166, 10, 0.6)',
            'rgba(29, 179, 44, 0.6)',
            'rgba(255, 255, 10, 0.6)',
            'rgba(29, 179, 44, 0.6)',
            'rgba(255, 59, 10, 0.6)',
            'rgba(255, 59, 10, 0.6)'
          ],
          borderWidth:1,
          borderColor:'#777',
          hoverBorderWidth:3,
          hoverBorderColor:'#000'
        }]
    },
    options: {
      responsive:true,
        title:{
          display:true,
          text:'Steps during the week',
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
          enabled:true
        }
    }
  }),

  mounted () {
    this.renderChart(this.chartdata, this.options)
  }
}
</script>