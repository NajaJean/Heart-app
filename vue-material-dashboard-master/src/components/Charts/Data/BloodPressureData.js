export function bloodPressureChartData(chart, thresholds) {
  return {
    type: 'line',
    data: {
      labels: chart[0].reverse(),
          datasets:[
            {
            label:'Systolic',
            data: chart[2].reverse(),
            backgroundColor:'#477b91',
            borderColor: '#477b91',
            fill: false,
            lineTension: 0,
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
            data: chart[1].reverse(),
            backgroundColor:'rgba(29, 179, 44, 0.6)',
            borderColor: 'rgba(29, 179, 44, 0.6)',
            fill: false,
            lineTension: 0,
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
          value: thresholds.blood_pressure_systoliclower,
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
          value: thresholds.blood_pressure_systolicupper,
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
          value: thresholds.blood_pressure_diastoliclower,
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
          value: thresholds.blood_pressure_diastolicupper,
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
  }
};

export default bloodPressureChartData;