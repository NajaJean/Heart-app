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
              backgroundColor: '#6ba8c2',
          borderColor: '#6ba8c2',
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
          backgroundColor: 'rgba(29, 179, 44, 0.6)',
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
        
      }, {scales: {
            yAxes: [{


                /*ticks: {
                  suggestedMin: Math.min(Math.min(this.chart[0]),Math.min(this.chart[1]),Math.min(this.thresholds[0]),Math.min(this.thresholds[1]))-20,
                  suggestedMax: Math.max(Math.max(this.chart[0]),Math.max(this.chart[1]),Math.max(this.thresholds[0]),Math.max(this.thresholds[1]))+20
                },*/

                afterDataLimits(scale) {
                  scale.max += 10;
                  scale.min -= 10 
                  //Math.min(Math.min(this.chart[0]),Math.min(this.chart[1]),this.thresholds.blood_pressure_systoliclower,this.thresholds.blood_pressure_systolicupper,this.thresholds.blood_pressure_diastoliclower,this.thresholds.blood_pressure_diastolicupper)-20;
                  
                }
            }]
          },
        
        
          responsive: true, 
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
              value: this.thresholds.blood_pressure_systoliclower,
              borderColor: '#91d2ed',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'Sys lower limit',
                position: 'right',
                xAdjust: 5
              },
              afterDataLimits(scale) {
                scale.max += 10;
                scale.min -= 10;
              }
            },{
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y-axis-0',
              id: 'sysuplimit',
              value: this.thresholds.blood_pressure_systolicupper,
              borderColor: '#457082',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'Sys upper limit',
                position: 'right',
                xAdjust: 5
              }
            },{
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y-axis-0',
              id: 'dialowlimit',
              value: this.thresholds.blood_pressure_diastoliclower,
              borderColor: '#29f23d',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'Dia lower limit',
                position: 'right',
                xAdjust: 5
              }
            },{
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y-axis-0',
              id: 'diauplimit',
              value: this.thresholds.blood_pressure_diastolicupper,
              borderColor: '#14801f',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'Dia upper limit',
                position: 'right',
                xAdjust: 5
              }
            }]
          },
        })
    }, 
}
</script>