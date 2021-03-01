const x = [];
const y = [];

var ctx = document.getElementById('ECGchart').getContext('2d');
const response = await fetch('data.csv');
    const data = await response.text();
	const years = [];
	const ecg = [];
	const rows = data.split('\n').slice(1);
	var count = 0;
	rows.forEach(row => {
		const cols = row.split(',');
		y.push(cols[0]);
		x.push(count);
		ecg.push(cols[0]);
		years.push(count);
		count = count+1; 
	});



var ECGchart = new Chart(ctx, {
type: 'line',
data: {
	labels: x,
	datasets: [{
		backgroundColor: 'rgba(255, 99, 132, 0.5)',
		borderColor: 'rgba(255, 99, 132, 1)',
		type: 'line',
		pointRadius: 0,
		fill: false,
		lineTension: 0,
		borderWidth: 2,
		label: '# ECG',

		data: y
	}]
},
options: {
	scales: {
		yAxes: [{
			ticks: {
				beginAtZero: true
			}
		}]
	}
}
});/*
async function getData() {
	// const response = await fetch('testdata.csv');
    const response = await fetch('data.csv');
    const data = await response.text();
	const years = [];
	const ecg = [];
	const rows = data.split('\n').slice(1);
	var count = 0;
	rows.forEach(row => {
		const cols = row.split(',');
		y.push(cols[0]);
		x.push(count);
		ecg.push(cols[0]);
		years.push(count);
		count = count+1; 
	});
	return { years, ecg };
};*/

