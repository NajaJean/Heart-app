let BloodPressure = document.getElementById('BloodPressure').getContext('2d');

    // Global Options
    Chart.defaults.global.defaultFontFamily = 'Lato';
    Chart.defaults.global.defaultFontSize = 18;
    Chart.defaults.global.defaultFontColor = '#777';

    let massPopChart3 = new Chart(BloodPressure, {
      type:'line', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
      data:{
        labels:['1. feb','2. feb','3. feb','4. feb','5. feb','6. feb','7. feb','8. feb','9. feb','10. feb'],
        datasets:[
			{
		   label:'Systolic',
          	data:[
				 110.234,
				 115.234,
				 105.234,
				 120.345,
				 127.324,
				 110.123,
				 120.345,
				 127.324,
				 135.924,
				 168.324
          ],
          backgroundColor:['rgba(255, 59, 10, 0.6)'
		  ],
		  borderColor: ['rgba(255, 59, 10, 0.6)'
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
        },{
		   label:'Diastolic',
          	data:[
			 82.876,
			  90.235,
			  93.978,
			  95.375,
			  91.234,
			  94.234,
			  82.095,
			  75.275,
			  71.945,
			  78.398
          ],
          backgroundColor:['rgba(29, 179, 44, 0.6)'
		  ],
		  borderColor: ['rgba(29, 179, 44, 0.6)'
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
          text:'Blood pressure during the week',
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
		},
		hover: {
          mode: 'point'
        }
      }
    });