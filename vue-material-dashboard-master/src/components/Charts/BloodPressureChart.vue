<template>
    <canvas id="BloodPressureChartCanvas" :width="370" :height="246"></canvas>
</template>

<script>
import { Chart } from 'chart.js';
import 'chartjs-adapter-moment'; // or another adapter to avoid moment
import annotationPlugin from 'chartjs-plugin-annotation';

Chart.register(annotationPlugin);

export default {
  props:['chart','thresholds'],
  name: 'BloodPressureChart',
  data() {
    return {
      //bloodPressureChartData: bloodPressureChartData(this.chart, this.thresholds)
    }
  },

  mounted() {
    const bloodPressureChartData = {
      type: 'line',
      data: {
        labels: this.chart[0].reverse(),
        datasets:[
          {
          label:'Systolic',
                data: this.chart[2].reverse(),
              backgroundColor:'#477b91',
          borderColor: '#477b91',
          fill: false,
          tension: 0,
          scaleShowValues: true,
          scales: {
            x: {
              ticks: {
                autoSkip: false
              }
            }
          },
        },{
          label:'Diastolic',
                data:this.chart[1].reverse(),
              backgroundColor:'rgba(29, 179, 44, 0.6)',
          borderColor: 'rgba(29, 179, 44, 0.6)',
          fill: false,
          tension: 0,
          scaleShowValues: true,
          scales: {
            x: {
              ticks: {
                autoSkip: false
              }
            }
          }
        }],
      },
      options: {
        plugins: {
          legend:{
            display:true,
            position:'bottom',
            labels:{
              fontColor:'#000'
            }
          },
          tooltip:{
            enabled:true,
            displayColors: true,
            callbacks: {
              label: function(context) {
                  let label = context.dataset.label || '';

                  if (label) {
                      label += ': ';
                  }
                  if (context.parsed.y !== null) {
                      label += context.parsed.y + ' mmHg';
                  }
                  return label;
              }
            }   
          },
          title:{
            display:false,
            text:'Blood pressure during the week',
            fontSize:25
          },
          annotation: {
          annotations: [{
            type: 'line',
            mode: 'horizontal',
            scaleID: 'y',
            id: 'syslowlimit',
            value: this.thresholds.blood_pressure_systoliclower,
            borderColor: 'rgb(75, 124, 192)',
            borderWidth: 2,
            borderDash: [5,2],
            label: {
              enabled: true,
              content: 'Sys lower limit',
              position: 'right',
              xAdjust: 5
            }
          },{
            type: 'line',
            mode: 'horizontal',
            scaleID: 'y',
            id: 'sysuplimit',
            value: this.thresholds.blood_pressure_systolicupper,
            borderColor: 'rgb(75, 192, 192)',
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
            scaleID: 'y',
            id: 'dialowlimit',
            value: this.thresholds.blood_pressure_diastoliclower,
            borderColor: 'rgb(75, 124, 192)',
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
            scaleID: 'y',
            id: 'diauplimit',
            value: this.thresholds.blood_pressure_diastolicupper,
            borderColor: 'rgb(75, 192, 192)',
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
        },
        scales: {
          x: {
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
            }
          }
        },
        axisX:{
          reversed: true
        },
        responsive: true, 
        maintainAspectRatio: false,
        layout:{
          padding:{
            left:50,
            right:0,
            bottom:0,
            top:0
          }
        },
        hover: {
          mode: 'point'
        },

      }
    };
    const ctx = document.getElementById('BloodPressureChartCanvas').getContext('2d');
    const chart = new Chart(ctx, bloodPressureChartData);
    chart.update();
  }
}
</script>