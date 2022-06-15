
import { StreamingPlugin } from 'chartjs-plugin-streaming';
import 'chartjs-adapter-date-fns';
import MeasurementDataService from '../../../services/MeasurementDataService';

var count = 0;

export const oldECGChartData = {
  type: 'line',
  data: {
    datasets: [{
      lastRecordedECG: [],
      data: [],
      label: 'ECG',
      pointHitRadius: 0,
      pointRadius: 0.5,
      tension: 0,
      backgroundColor:'#004346',
      borderColor: '#004346',
      fill: false,
    }]
  },
  options: {
    plugins: {
      // Change options for ALL axes of THIS CHART
      streaming: {
        frameRate: 20
      }
    },
    scales: {
      x: {
        reverse: true,
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
        },
        type: 'realtime',
        realtime: {
          duration: 5000,
          delay: 2000,
          // refresh: 1000,
          onRefresh: chart => {
            chart.data.datasets.forEach(function(dataset) {
              MeasurementDataService.getOldECG("1")
              .then(response => {
                  const ecg = response.data[count%5000];
                  //const datepost = (ecg.datepost).substring(0, ecg.datepost.length - 6) + "-02:00";

                  if (dataset.lastRecordedECG != ecg && ecg) {
                    var ptime = new Date();
                    for (var i = 0; i < ecg.measurementvalue.length - 1; i++) {
                      ptime = new Date(ptime.getTime() + 7.8125)
                      dataset.data.push({
                      x: ptime,
                      y: ecg.measurementvalue[i]
                      })
                    }

                  chart.update();
                  dataset.lastRecordedECG = ecg; 
                }     
              })
              .catch(e => {
                console.log(e);
              });
              count++;
            });
          }
        }
      }
    }
  }, plugins: [StreamingPlugin]
};

export default oldECGChartData;