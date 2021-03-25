<script>
import { Line } from 'vue-chartjs'
import * as ChartAnnotation from 'chartjs-plugin-annotation';

export default {
  extends: Line,
  props:['chart','thresholds'],
  mounted () {
      this.renderChart({
        labels: this.chart[0],
        datasets:[
          {
          label:'Systolic',
                data: this.chart[1],
              backgroundColor:'#004346',
          borderColor: '#004346',
          fill: false,
          tension: 0,
          scaleShowValues: true,
          scales: {
            xAxes: [{
            ticks: {
              autoSkip: false
            }
            }]
          },
        },{
          label:'Diastolic',
                data:this.chart[2],
              backgroundColor:'rgba(29, 179, 44, 0.6)',
          borderColor: 'rgba(29, 179, 44, 0.6)',
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
            display:false,
            text:'Blood pressure during the week',
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
                  return data.datasets[tooltipItem.datasetIndex].label+ ': ' + data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index] + ' mmHg';
              }
            }   
          },
          hover: {
            mode: 'point'
          },
          annotation: {
            annotations: [{
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y-axis-0',
              id: 'syslowlimit',
              value: this.thresholds.blood_pressure_systolic[0],
              borderColor: 'rgb(75, 124, 192)',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'Sys lower limit',
                xAdjust: 325
              }
            },{
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y-axis-0',
              id: 'sysuplimit',
              value: this.thresholds.blood_pressure_systolic[1],
              borderColor: 'rgb(75, 192, 192)',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'Sys upper limit',
                xAdjust: 325
              }
            },{
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y-axis-0',
              id: 'dialowlimit',
              value: this.thresholds.blood_pressure_diastolic[0],
              borderColor: 'rgb(75, 124, 192)',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'Dia lower limit',
                xAdjust: 325
              }
            },{
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y-axis-0',
              id: 'diauplimit',
              value: this.thresholds.blood_pressure_diastolic[1],
              borderColor: 'rgb(75, 192, 192)',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'Dia upper limit',
                xAdjust: 325
              }
            }]
          },
        })
    }, 
}
</script>