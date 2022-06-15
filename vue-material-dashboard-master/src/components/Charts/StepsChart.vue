<template>
    <canvas id="StepsChartCanvas" :width="370" :height="246"></canvas>
</template>

<script>
import { Chart } from 'chart.js';
import 'chartjs-adapter-moment'; // or another adapter to avoid moment
import annotationPlugin from 'chartjs-plugin-annotation';
import 'chartjs-adapter-date-fns';

Chart.register(annotationPlugin);

export default {
  props:['chart','thresholds'],
  name: 'stepsChart',
  data() {
    return {
      //oldECGData: oldECGData
    }
  },
  mounted() {
    const stepsChartData = {
      type: 'bar',
      data: {
        labels: this.chart[0],
        datasets:[
          {
          label:'Steps',
          data: this.chart[1],
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
        }]
      },
      options: {
        plugins: {
          title:{
            display:false,
            text:'Steps during the week',
            fontSize:25
          },
          legend:{
            display:false,
            position:'bottom',
            labels:{
              fontColor:'#000'
            }
          },
          tooltip:{
            displayColors: false,
            enabled: true
          },
          annotation: {
            annotations: [{
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y',
              id: 'lowlimit',
              value: this.thresholds.cnt_stepslower,
              borderColor: 'rgb(75, 124, 192)',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'lower limit',
                position: 'right',
                xAdjust: 5
              }
            },{
              type: 'line',
              mode: 'horizontal',
              scaleID: 'y',
              id: 'uplimit',
              value: this.thresholds.cnt_stepsupper,
              borderColor: 'rgb(75, 192, 192)',
              borderWidth: 2,
              borderDash: [5,2],
              label: {
                enabled: true,
                content: 'upper limit',
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
          },
        },
      }
    };
    const ctx = document.getElementById('StepsChartCanvas').getContext('2d');
    new Chart(ctx, stepsChartData);
  }
}

// export default {
//   extends: Bar,
//   props:['chart','thresholds'],
//   mounted () {
//       this.renderChart({
//         labels: this.chart[0],
//         datasets:[
//           {
//           label:'Steps',
//           data:this.chart[1],
//           backgroundColor:'rgba(29, 179, 44, 0.6)',
//           borderColor: 'rgba(29, 179, 44, 0.6)',
//           fill: false,
//           tension: 0,
//           scaleShowValues: true,
//           scales: {
//             xAxes: [{
//             ticks: {
//               autoSkip: false
//             }
//             }]
//           }
//         }],      
//       }, {
//         responsive:true,
//         maintainAspectRatio: false,
//         title:{
//           display:false,
//           text:'Steps during the week',
//           fontSize:25
//         },
//         legend:{
//           display:false,
//           position:'bottom',
//           labels:{
//             fontColor:'#000'
//           }
//         },
//         layout:{
//           padding:{
//             left:50,
//             right:0,
//             bottom:0,
//             top:0
//           },
//         },
//         tooltips:{
//           displayColors: false,
//           enabled: true
//         },
//         annotation: {
//             annotations: [{
//               type: 'line',
//               mode: 'horizontal',
//               scaleID: 'y-axis-0',
//               id: 'lowlimit',
//               value: this.thresholds.cnt_stepslower,
//               borderColor: 'rgb(75, 124, 192)',
//               borderWidth: 2,
//               borderDash: [5,2],
//               label: {
//                 enabled: true,
//                 content: 'lower limit',
//                 position: 'right',
//                 xAdjust: 5
//               }
//             },{
//               type: 'line',
//               mode: 'horizontal',
//               scaleID: 'y-axis-0',
//               id: 'uplimit',
//               value: this.thresholds.cnt_stepsupper,
//               borderColor: 'rgb(75, 192, 192)',
//               borderWidth: 2,
//               borderDash: [5,2],
//               label: {
//                 enabled: true,
//                 content: 'upper limit',
//                 position: 'right',
//                 xAdjust: 5
//               }
//             }]
//           }
//       })
//     },
// }
</script>