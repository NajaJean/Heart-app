<template>
  <div>
    <canvas id="ECGChartCanvas" :width="370" :height="246"></canvas>
    <md-button name="togglePause" class="md-dense md-raised md-info md-just-icon" @click="togglePause()">
      <md-icon v-if="!this.paused">pause</md-icon>
      <md-icon v-if="this.paused">play_arrow</md-icon>
    </md-button>
  </div>
</template>

<script>
import { Chart, registerables } from 'chart.js';
import 'chartjs-adapter-moment'; // or another adapter to avoid moment
Chart.register(...registerables);

import ChartStreaming from 'chartjs-plugin-streaming';
import 'chartjs-adapter-date-fns';

Chart.register(ChartStreaming);

import { StreamingPlugin } from 'chartjs-plugin-streaming';
import MeasurementDataService from '../../services/MeasurementDataService';

var char;

export default {
  name: 'ECGChart',
  data() {
    return {
      paused: false
    }
  },
  methods: {
    togglePause() {
      char.options.plugins.streaming.pause = !this.paused;
      this.paused = !this.paused;
      char.update();
    }
  },
  mounted() {
    const ECGChartData = {
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
          },
          title:{
            display:false,
            text:'Live ECG',
            fontSize:25
          },
          legend:{
            display:true,
            position:'bottom',
            labels:{
              fontColor:'#000'
            }
          },
          tooltips:{
            enabled: false,
          },
        },
        responsive: true, 
        maintainAspectRatio: true,
        layout:{
          padding:{
            left:50,
            right:0,
            bottom:0,
            top:0
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
                    const ecg = response.data[0];
                    const datepost = (ecg.datepost).substring(0, ecg.datepost.length - 6) + "-02:00";

                    var ptime = new Date((new Date(datepost)).getTime());
                    if (dataset.lastRecordedTime <= new Date(datepost)) {
                    
                        for (var i = 0; i < ecg.measurementvalue.length - 1; i++) {
                          ptime = new Date(ptime.getTime() + 7.8125)

                          dataset.data.push({
                          x: ptime,
                          y: ecg.measurementvalue[i]
                          })
                        }

                    chart.update();
                    dataset.lastRecordedTime = new Date(datepost);
                    }     
                  })
                  .catch(e => {
                    console.log(e);
                  });
                });
              }
            }
          }
        }
      }, plugins: [StreamingPlugin]
    };
    const ctx = document.getElementById('ECGChartCanvas').getContext('2d');
    char = new Chart(ctx, ECGChartData);
  }
}
</script> 