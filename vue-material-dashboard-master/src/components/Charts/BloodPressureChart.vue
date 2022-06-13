<template>
  <div>
    <canvas id="BloodPressureChartCanvas"></canvas>
  </div>
</template>

<script>
import { Chart, registerables } from 'chart.js';
import 'chartjs-adapter-moment'; // or another adapter to avoid moment
import Annotation from 'chartjs-plugin-annotation';
Chart.register(...registerables, Annotation);
//import ChartStreaming from 'chartjs-plugin-streaming';
import bloodPressureChartData from './Data/BloodPressureData';


//Chart.register(ChartStreaming);


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
          reversed:  false
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
            scaleID: 'y-axis-0',
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
            scaleID: 'y-axis-0',
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
            scaleID: 'y-axis-0',
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
      }
    };
    const ctx = document.getElementById('BloodPressureChartCanvas');
    new Chart(ctx, bloodPressureChartData);
  }
}


// export default {
//   extends: Line,
//   props:['chart','thresholds'],
//   mounted () {
//       this.renderChart({
//         labels: this.chart[0].reverse(),
//         datasets:[
//           {
//           label:'Systolic',
//                 data: this.chart[2].reverse(),
//               backgroundColor:'#477b91',
//           borderColor: '#477b91',
//           fill: false,
//           tension: 0,
//           scaleShowValues: true,
//           scales: {
//             xAxes: [{
//             ticks: {
//               autoSkip: false
//             }
//             }]
//           },
//         },{
//           label:'Diastolic',
//                 data:this.chart[1].reverse(),
//               backgroundColor:'rgba(29, 179, 44, 0.6)',
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
        
//       }, {scales: {
//             xAxes: [{
//               ticks: {
//                 autoSkip: true,
//                 maxTicksLimit: 15
//               },
//               time: {
//                 format: undefined,
//                 displayFormats: {
//                     millisecond: 'H:mm:ss.SSS',
//                     second: 'H:mm:ss',
//                     minute: 'H:mm',
//                     hour: 'H'
//                 }
//               }
//             }]
//       },
//           axisX:{
//    reversed:  false
//  },
//           responsive: true, 
//           maintainAspectRatio: false,
//           title:{
//             display:false,
//             text:'Blood pressure during the week',
//             fontSize:25
//           },
//           legend:{
//             display:true,
//             position:'bottom',
//             labels:{
//               fontColor:'#000'
//             }
//           },
//           layout:{
//             padding:{
//               left:50,
//               right:0,
//               bottom:0,
//               top:0
//             }
//           },
//           tooltips:{
//             enabled:true,
//             displayColors: true,
//             callbacks: {
//               label: function(tooltipItem, data) {
//                   return data.datasets[tooltipItem.datasetIndex].label+ ': ' + data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index] + ' mmHg';
//               }
//             }   
//           },
//           hover: {
//             mode: 'point'
//           },
//           annotation: {
//             annotations: [{
//               type: 'line',
//               mode: 'horizontal',
//               scaleID: 'y-axis-0',
//               id: 'syslowlimit',
//               value: this.thresholds.blood_pressure_systoliclower,
//               borderColor: 'rgb(75, 124, 192)',
//               borderWidth: 2,
//               borderDash: [5,2],
//               label: {
//                 enabled: true,
//                 content: 'Sys lower limit',
//                 position: 'right',
//                 xAdjust: 5
//               }
//             },{
//               type: 'line',
//               mode: 'horizontal',
//               scaleID: 'y-axis-0',
//               id: 'sysuplimit',
//               value: this.thresholds.blood_pressure_systolicupper,
//               borderColor: 'rgb(75, 192, 192)',
//               borderWidth: 2,
//               borderDash: [5,2],
//               label: {
//                 enabled: true,
//                 content: 'Sys upper limit',
//                 position: 'right',
//                 xAdjust: 5
//               }
//             },{
//               type: 'line',
//               mode: 'horizontal',
//               scaleID: 'y-axis-0',
//               id: 'dialowlimit',
//               value: this.thresholds.blood_pressure_diastoliclower,
//               borderColor: 'rgb(75, 124, 192)',
//               borderWidth: 2,
//               borderDash: [5,2],
//               label: {
//                 enabled: true,
//                 content: 'Dia lower limit',
//                 position: 'right',
//                 xAdjust: 5
//               }
//             },{
//               type: 'line',
//               mode: 'horizontal',
//               scaleID: 'y-axis-0',
//               id: 'diauplimit',
//               value: this.thresholds.blood_pressure_diastolicupper,
//               borderColor: 'rgb(75, 192, 192)',
//               borderWidth: 2,
//               borderDash: [5,2],
//               label: {
//                 enabled: true,
//                 content: 'Dia upper limit',
//                 position: 'right',
//                 xAdjust: 5
//               }
//             }]
//           },
//         })
//     }, 
// }
</script>