<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33"
      >
        <blood-pressure-chart v-if="this.dataloaded" :width="370" :height="246" :chart="bpchartdata"></blood-pressure-chart> 
      </div>
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33"
      >
        <steps-chart v-if="this.dataloaded" :width="370" :height="246" :chart="stepchartdata"></steps-chart>
      </div>
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33"
      >
        <sleep-chart v-if="this.dataloaded" :width="370" :height="246" :chart="sleepchartdata"></sleep-chart>
      </div>
    </div>
  </div>
</template>




<script>
import DailyRecordingDataService from "../services/DailyRecordingDataService";
import BloodPressureChart from '../components/Charts/BloodPressureChart.vue';
import StepsChart from '../components/Charts/StepsChart.vue';
import SleepChart from '../components/Charts/SleepChart.vue';

export default {
  components: {
    BloodPressureChart,
    StepsChart,
    SleepChart
  },
  data() {
    return {
      bpchartdata: [],
      stepchartdata: [],
      sleepchartdata: [],
      dataloaded: false
    };
  },
  methods: {
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
          response.data.map(dr => dates.push(dr.date_post.substring(0,3)));
          response.data.map(dr => bps.push(dr.blood_pressure_systolic));
          response.data.map(dr => bpd.push(dr.blood_pressure_diastolic));
          response.data.map(dr => steps.push(dr.cnt_steps));
          response.data.map(dr => sleepDeep.push(dr.sleep_deep*60));
          response.data.map(dr => sleepREM.push(dr.sleep_rem*60));
          response.data.map(dr => sleepLight.push(dr.sleep_light*60));

          this.bpchartdata = [dates,bpd,bps];
          this.stepchartdata = [dates,steps];
          this.sleepchartdata = [dates,sleepLight,sleepREM,sleepDeep];
          console.log(sleepREM);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  async created() {
    this.retrieveDailyRecordings();
    await sleep(4000);
    this.dataloaded = true;
  }
};

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}
</script>
