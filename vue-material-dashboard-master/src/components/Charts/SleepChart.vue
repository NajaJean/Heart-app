<template>
    <canvas id="SleepChartCanvas" :width="370" :height="246"></canvas>
</template>

<script>
import { Chart } from 'chart.js';
import 'chartjs-adapter-moment'; // or another adapter to avoid moment
import annotationPlugin from 'chartjs-plugin-annotation';

Chart.register(annotationPlugin);

var char;
var totalSleep;
export default {
  props:['chart','thresholds'],
  name: 'SleepChart',
  data() {
    return {
      char: this.chart
    }
  },
  mounted() {
    totalSleep = this.chart[4];
    const SleepChartData = {
      type: 'bar',
      data: {
        labels: this.chart[0],
        datasets:[
          {
          label:'Light',
          data:this.chart[1].map( i => Number(i).toFixed(2)),
          yAxisID: 'y',
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
          data:this.chart[2].map( i => Number(i).toFixed(2)),
          yAxisID: 'y',
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
          data:this.chart[3].map( i => Number(i).toFixed(2)),
          yAxisID: 'y',
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
          },
        }
      ],
      },
      options: {
        plugins: {
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
          tooltip:{
            enabled:true,
            displayColors: false,
            callbacks: {
                label: function(context) {
                  let label = context.dataset.label || '';

                  if (label) {
                      label += ': ';
                  }
                  if (context.parsed.y !== null) {
                      label += context.parsed.y + ' hours';
                  }
                  return label;
              }
              }  
          },
          annotation: {
            annotations: [{
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y',
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
              scaleID: 'y',
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
              scaleID: 'y',
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
              scaleID: 'y',
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
              scaleID: 'y',
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
              scaleID: 'y',
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
        },
        responsive:true,
        maintainAspectRatio: false,
        layout:{
          padding:{
            left:50,
            right:0,
            bottom:0,
            top:0
          }
        },
        scales: {
          x1: {
            id: 'x1'
          },
          x2: {
            display: true,
            position: 'top',
            id: 'x2',
          },
        }
      },
    };
    const ctx = document.getElementById('SleepChartCanvas').getContext('2d');
    char = new Chart(ctx, SleepChartData);

    function insertTotalSleep(index) {
      return 'Total: '+totalSleep[index]+'h';
    }

    char.options.scales.x2.ticks.callback = insertTotalSleep;
    char.update();
  }
}
</script>
