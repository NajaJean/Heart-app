<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-xlarge-size-100 md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-33">
        <md-button class="md-dense md-raised md-info" @click="togglePause()">{{!this.pause ? "Pause" : "Start"}}</md-button>
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title" font-weight="bold">Live ECG</h2>
          </md-card-header>
          <md-card-content>
            <h2 :key="heartRate" class="title" font-weight="bold" text-align="center">Heart Rate: {{this.heartRate}}</h2>
            <e-c-g-chart :key="pause" name='ecg-chart' :width="370" :height="246" :pauseX="pause"></e-c-g-chart> 
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>
import ECGChart from '../components/Charts/ECGChart.vue';
import MeasurementDataService from "../services/MeasurementDataService";

export default {
  components: {
    ECGChart
  },
  data() {
    return {
      pause: false,
      heartRate: null
    };
  },
  created() {
    this.interval = setInterval(() => this.getHeartRate(), 500);
  },
  methods: {
    togglePause() {
      //this.ECGChart.options.scales.xAxes.realtime.pause = true,
      this.pause = !this.pause;
      console.log("pause: "+this.pause);
    },
    getHeartRate() {
      MeasurementDataService.getLatestECG("1")
        .then(response => {
          const newrate = response.data[0].measurementvalue[response.data[0].measurementvalue.length-1];
          if (this.heartRate != newrate) {
            this.heartRate = newrate;
          }     
        })
        .catch(e => {
          console.log(e);
        });
    } 
  }
};
</script>