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
            <blood-pressure-chart :key="keyvalue" :width="370" :height="246" :chart="[dates,data.blood_pressure_diastolic,data.blood_pressure_systolic]" :thresholds="thresholds"></blood-pressure-chart> 
          </md-card-content>
        </md-card>
      </div>
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33">
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title">Steps</h2>
          </md-card-header>
          <md-card-content>
            <steps-chart :key="keyvalue" :width="370" :height="246" :chart="[dates,data.cnt_steps]" :thresholds="thresholds"></steps-chart>
          </md-card-content>
        </md-card>
      </div>
      <div class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33">
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title">Sleep</h2>
          </md-card-header>
          <md-card-content>
            <sleep-chart :key="keyvalue" :width="370" :height="246" :chart="[dates,data.sleep_light,data.sleep_rem,data.sleep_deep]" :thresholds="thresholds"></sleep-chart>
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>
import MeasurementDataService from "../services/MeasurementDataService";
import ThresholdDataService from "../services/ThresholdDataService";
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
      data: {},
      dates: [],
      setThresholds: false,
      dataloaded: false,
      thresholds: {},//new Array(12),
      keyvalue: 0,
      patient_id: '010101-1234',
      measurement_types: ['blood_pressure_diastolic','blood_pressure_systolic','cnt_steps','sleep_light','sleep_rem','sleep_deep'],
    };
  },
  methods: {
    toggleThresholdsForm() {
      this.setThresholds = !this.setThresholds;
    },
    updateCharts() {
      this.keyvalue = this.keyvalue+1;
    },
    newThreshold(newThreshold) {
      this.thresholds = newThreshold;

      //use ThresholdDataService.update(id, data)

      console.log("Thresholds updated");
      this.updateCharts();
      this.toggleThresholdsForm();
    },
    retrieveMeasurements() {
      var typ;
      for (typ in (this.measurement_types)) {
        MeasurementDataService.get7Latest(this.patient_id,this.measurement_types[typ])
        .then(response => {
          response.data.map(m => this.pushMeasurementsIntoData(m));
        })
        .catch(e => {
          console.log(e);
        });
      }
    },
    pushMeasurementsIntoData(m) {
      if (this.data[m.measurement_type]==null) {
        this.data[m.measurement_type] = []
        this.thresholds[m.measurement_type] = [null,null]
      }
      this.data[m.measurement_type].push(m.measurement_value);
      
      //special case for dates, need to be fixed someday
      if (m.measurement_type == "cnt_steps") {
        this.dates.push(m.date_post.substring(0,11));
      }
    },
    retrieveThresholds() {
      ThresholdDataService.getAll()
        .then(response => {
          //No content
          if (response.status!=204) {
            response.data.map(t => this.insertThreshold(t)); 
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    insertThreshold(t) {
      this.thresholds[t.measurement_type].push([t.lower_threshold,t.upper_threshold]);
    }
  },
  async created() {
    this.retrieveMeasurements();
    this.retrieveThresholds();
    await sleep(2000);
    this.dataloaded = true;
  }
};

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}
</script>

