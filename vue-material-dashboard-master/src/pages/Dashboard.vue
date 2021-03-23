<template>
  <div class="content">
    <div class="md-layout" v-if="!this.dataloaded">
      <h1>Loading ...</h1>
    </div>
    <div class="md-layout" v-if="this.dataloaded">
      <md-button class="md-dense md-raised md-info" @click="toggleThresholdsForm()">{{this.setThresholds ? "Cancel" : "Set Thresholds"}}</md-button>
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33">
        <threshold-form v-if="this.setThresholds" :thresholds="thresholds" @new-threshold="newThreshold"></threshold-form>
      </div>
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33">
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title" font-weight="bold">Blood Pressure During the Week</h2>
          </md-card-header>
          <md-card-content>
            <blood-pressure-chart :key="keyvalue" :width="370" :height="246" :chart="[dates,bpd,bps]" :thresholds="thresholds"></blood-pressure-chart> 
          </md-card-content>
        </md-card>
      </div>
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33">
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title">Steps</h2>
          </md-card-header>
          <md-card-content>
            <steps-chart :key="keyvalue" :width="370" :height="246" :chart="[dates,steps]" :thresholds="thresholds"></steps-chart>
          </md-card-content>
        </md-card>
      </div>
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33">
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title">Sleep</h2>
          </md-card-header>
          <md-card-content>
            <sleep-chart :key="keyvalue" :width="370" :height="246" :chart="[dates,sleepLight,sleepREM,sleepDeep]" :thresholds="thresholds"></sleep-chart>
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>
import MeasurementDataService from "../services/MeasurementDataService";
import BloodPressureChart from '../components/Charts/BloodPressureChart.vue';
import StepsChart from '../components/Charts/StepsChart.vue';
import SleepChart from '../components/Charts/SleepChart.vue';
import ThresholdForm from '../components/ThresholdForm.vue';

export default {
  components: {
    BloodPressureChart,
    StepsChart,
    SleepChart,
    ThresholdForm
  },
  data() {
    return {
      dates: [],
      bpd: [],
      bps: [],
      steps: [],
      sleepLight: [],
      sleepREM: [],
      sleepDeep: [],
      setThresholds: false,
      dataloaded: false,
      thresholds: new Array(12),
      keyvalue: 0,
    };
  },
  methods: {
    toggleThresholdsForm() {
      this.setThresholds = !this.setThresholds;
    },
    newThreshold(newThreshold) {
      this.thresholds = newThreshold;
      this.keyvalue = this.kvalue+1;
      console.log("Thresholds updated");
      this.toggleThresholdsForm()
    },
    pushDataIntoArray(m) {
      if (m.measurement_type == "cnt_steps") {
        this.dates.push(m.date_post.substring(0,11));
        this.steps.push(m.measurement_value);
      }
      else if (m.measurement_type == "blood_pressure_diastolic") {
        this.bpd.push(m.measurement_value);
      }
      else if (m.measurement_type == "blood_pressure_systolic") {
        this.bps.push(m.measurement_value);
      }
      else if (m.measurement_type == "sleep_light") {
        this.sleepLight.push((m.measurement_value*60).toFixed(2));
      }
      else if (m.measurement_type == "sleep_rem") {
        this.sleepREM.push((m.measurement_value*60).toFixed(2));
      }
      else if (m.measurement_type == "sleep_deep") {
        this.sleepDeep.push((m.measurement_value*60).toFixed(2));
      }
    },
    retrieveMeasurements() {
      MeasurementDataService.getAll()
        .then(response => {
          response.data.slice(0, 7*6).map(m => this.pushDataIntoArray(m));
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  async created() {
    this.retrieveMeasurements();
    await sleep(2000);
    this.dataloaded = true;
  }
};

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}
</script>

