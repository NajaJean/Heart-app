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
            <blood-pressure-chart :key="keyvalue" :width="370" :height="246" :chart="bpchartdata" :thresholds="thresholds"></blood-pressure-chart> 
          </md-card-content>
        </md-card>
      </div>
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33">
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title">Steps</h2>
          </md-card-header>
          <md-card-content>
            <steps-chart :width="370" :height="246" :chart="stepchartdata" :thresholds="thresholds"></steps-chart>
          </md-card-content>
        </md-card>
      </div>
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33">
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title">Sleep</h2>
          </md-card-header>
          <md-card-content>
            <sleep-chart :width="370" :height="246" :chart="sleepchartdata" :thresholds="thresholds"></sleep-chart>
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>
import DailyRecordingDataService from "../services/DailyRecordingDataService";
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
      bpchartdata: [],
      stepchartdata: [],
      sleepchartdata: [],
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
    async retrieveDailyRecordings() {
      await sleep(1000);
      DailyRecordingDataService.getAll()
        .then(response => {
          var dates = [];
          var bps = [];
          var bpd = [];
          var steps = [];
          var sleepLight = [];
          var sleepREM = [];
          var sleepDeep = [];
          response.data.map(dr => dates.push(dr.date_post.substring(0,11)));
          response.data.map(dr => bps.push(dr.blood_pressure_systolic));
          response.data.map(dr => bpd.push(dr.blood_pressure_diastolic));
          response.data.map(dr => steps.push(dr.cnt_steps));
          response.data.map(dr => sleepDeep.push(dr.sleep_deep*60));
          response.data.map(dr => sleepREM.push(dr.sleep_rem*60));
          response.data.map(dr => sleepLight.push(dr.sleep_light*60));

          this.bpchartdata = [dates,bpd,bps];
          this.stepchartdata = [dates,steps];
          this.sleepchartdata = [dates,sleepLight,sleepREM,sleepDeep];
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  async created() {
    this.retrieveDailyRecordings();
    await sleep(2000);
    this.dataloaded = true;
  }
};

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}
</script>

