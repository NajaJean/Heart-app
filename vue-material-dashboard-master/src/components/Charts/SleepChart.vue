<script>
import { Bar } from 'vue-chartjs';
import ChartDataLabels from 'chartjs-plugin-datalabels';

export default {
  extends: Bar,
  props:['chart','thresholds'],
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
          display:false,
          text:'Sleep during the week',
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
          enabled:true,
          displayColors: true,
          callbacks: {
              label: function(tooltipItem, data) {
                  return [data.datasets[tooltipItem.datasetIndex].label+ ': ' + data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index] + ' min','Total: '];
              }
            }  
        },
        annotation: {
            annotations: [{
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y-axis-0',
              id: 'lightlowlimit',
              value: this.thresholds.sleep_lightlower,
              borderColor: 'rgb(75, 124, 192)',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'light lower limit',
                position: 'right',
                xAdjust: 5
              }
            },{
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y-axis-0',
              id: 'lightuplimit',
              value: this.thresholds.sleep_lightupper,
              borderColor: 'rgb(75, 192, 192)',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'light upper limit',
                position: 'right',
                xAdjust: 5
              }
            },
            {
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y-axis-0',
              id: 'remlowlimit',
              value: this.thresholds.sleep_remlower,
              borderColor: 'rgb(75, 124, 192)',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'rem lower limit',
                position: 'right',
                xAdjust: 5
              }
            },{
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y-axis-0',
              id: 'remuplimit',
              value: this.thresholds.sleep_remupper,
              borderColor: 'rgb(75, 192, 192)',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'rem upper limit',
                position: 'right',
                xAdjust: 5
              }
            },
            {
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y-axis-0',
              id: 'deeplowlimit',
              value: this.thresholds.sleep_deeplower,
              borderColor: 'rgb(75, 124, 192)',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'deep lower limit',
                position: 'right',
                xAdjust: 5
              }
            },{
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y-axis-0',
              id: 'deepuplimit',
              value: this.thresholds.sleep_deepupper,
              borderColor: 'rgb(75, 192, 192)',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'deep upper limit',
                position: 'right',
                xAdjust: 5
              }
            }]
        }
      })
    },
}
</script>