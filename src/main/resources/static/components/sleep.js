let Sleep = document.getElementById('Sleep').getContext('2d');

    // Global Options
    Chart.defaults.global.defaultFontFamily = 'Lato';
    Chart.defaults.global.defaultFontSize = 18;
    Chart.defaults.global.defaultFontColor = '#777';

    let massPopChart4 = new Chart(Sleep, {
      type:'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
      data:{
        labels:['1. feb','2. feb','3. feb','4. feb','5. feb','6. feb','7. feb','8. feb','9. feb','10. feb'],
        datasets:[
			{
           label:'Light',
          	data:[
				 145.234,
				 178.234,
				 100.234,
				 190.345,
				 165.324,
				 144.123,
				 170.345,
				 175.324,
				 115.924,
				 108.324
          ],
          backgroundColor:['rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)'
		  ],
		  borderColor: ['rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)','rgba(65,105,225, 0.6)'
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
           label:'RAM',
          	data:[
				 90.234,
				 75.234,
				 82.234,
				 50.345,
				 120.324,
				 89.123,
				 60.345,
				 93.324,
				 106.924,
				 68.324
          ],
          backgroundColor:['rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)'
		  ],
		  borderColor: ['rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)','rgba(255, 59, 10, 0.6)'
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
           label:'Deep',
          	data:[
			 130.876,
			  166.235,
			  155.978,
			  120.375,
			  147.234,
			  186.234,
			  133.095,
			  119.275,
			  155.945,
			  166.398
          ],
          backgroundColor:['rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)'
		  ],
		  borderColor: ['rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)','rgba(148,0,211, 0.6)'
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
          text:'Sleep',
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
		  displayColors: true
        }
      }
    });