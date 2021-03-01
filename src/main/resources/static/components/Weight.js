let Weight = document.getElementById('Weight').getContext('2d');

    // Global Options
    Chart.defaults.global.defaultFontFamily = 'Lato';
    Chart.defaults.global.defaultFontSize = 18;
    Chart.defaults.global.defaultFontColor = '#777';

    let massPopChart2 = new Chart(Weight, {
      type:'line', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
      data:{
        labels:['1. feb','2. feb','3. feb','4. feb','5. feb','6. feb','7. feb','8. feb','9. feb','10. feb'],
        datasets:[{
          label:'Weight (kg)',
          	data:[
            80.225,
			80.465,
			81.015,
			81.225,
			80.855,
			80.745,
			80.095,
			80.465,
			80.725,
			80.995
          ],
          backgroundColor:[
            'rgba(29, 179, 44, 0.6)'
		  ],
		  borderColor: [
            'rgba(29, 179, 44, 0.6)'
		  ],
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
        }]
      },
      options:{
		responsive:true,
		maintainAspectRatio: false,
        title:{
          display:true,
          text:'Weight during the week',
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
		  displayColors: false
        }
      }
    });