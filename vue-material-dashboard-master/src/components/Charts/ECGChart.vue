<template>
  <div>
    <canvas id="ECGChartCanvas" :width="370" :height="146"></canvas>
    <div class="md-layout-item md-size-100" style="text-align: right; margin-bottom: -15px;">    
      <md-button name="togglePause" class="md-dense md-raised md-info md-just-icon" @click="togglePause()">
        <md-icon v-if="!this.paused">pause</md-icon>
        <md-icon v-if="this.paused">play_arrow</md-icon>
      </md-button> 
    </div>
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
      paused: false,
    }
  },
  methods: {
    togglePause() {
      this.paused = !this.paused;
      char.options.plugins.streaming.pause = this.paused;
      this.$emit('pause-heart-rate', this.paused)
      char.update();
    }
  },
  mounted() {
    const ECGChartData = {
      type: 'line',
      data: {
        datasets: [{
          lastRecordedTime: new Date(),
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
            pause: this.paused,
            frameRate: 20
          },
          title:{
            display:false,
            text:'Live ECG',
            fontSize:25
          },
          legend:{
            display:false,
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
          y: {
            min: -3000,
            max: 3000
          },
          x: {
            reverse: true,
            ticks: {
              display: false,
              autoSkip: true,
              maxTicksLimit: 15
            },
            time: {
              format: undefined,
              displayFormats: {
                  millisecond: 'H:mm:ss.S',
                  second: 'H:mm:ss',
                  minute: 'H:mm',
                  hour: 'H'
              }
            },
            type: 'realtime',
            realtime: {
              duration: 5000,
              delay: 2000,
              refresh: 1000,
              onRefresh: chart => {
                chart.data.datasets.forEach(function(dataset) {
                  MeasurementDataService.getLatestECG("1")
                  .then(response => {
                    const ecg = response.data[0];
                    const datepost = (ecg.datepost).substring(0, ecg.datepost.length - 6) + "-02:00";
                    var ptime = new Date((new Date(datepost)).getTime());
                    if (dataset.lastRecordedTime <= new Date(datepost)) {
                      const ecgLength = ecg.measurementvalue.length - 1;
                    
                      for (var i = 0; i < ecgLength; i++) {
                        ptime = new Date(ptime.getTime() + 7.8125)
                        dataset.data.push({
                        x: ptime,
                        y: ecg.measurementvalue[ecgLength-i]
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