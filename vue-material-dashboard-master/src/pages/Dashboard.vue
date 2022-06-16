<template>
  <div class="content">
    <div class="md-layout" v-if="!this.dataloaded">
      <h1>Loading ...</h1>
    </div>
    <div class="md-layout" v-if="this.dataloaded">
      <div class="md-layout">
        <div class="md-layout-item" style="text-align: right">
          <md-switch v-model="aauData" @change="toggleAauData()">Live data</md-switch>
        </div>
      </div>
      
      
        <md-field class="md-layout-item md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-33">
          <label>CPR</label>
          <md-input name="patientID" v-model="patient_id"></md-input>
          <md-button name="changePatient" class="md-dense md-raised md-info" @click="changePatient(patient_id)">Change Patient</md-button>
          <md-button name="thresholdButton" class="md-dense md-raised md-info" @click="toggleThresholdsForm()">{{this.setThresholds ? "Cancel" : "Set Thresholds"}}</md-button>
        </md-field>
      
      <div class="md-layout-item md-xlarge-size-100 md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-33">
        <threshold-form :key="keyvalue" v-if="this.setThresholds" :thresholds="thresholds" @new-threshold="newThreshold"></threshold-form>
      </div>
      <date-picker :key="keyvalue" :selectedfrom="selectedFrom" :selectedto="selectedTo" @update-time="updateTime"></date-picker>
      <div class="md-layout-item md-xlarge-size-100 md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-33">
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title" font-weight="bold">Blood Pressure</h2>
          </md-card-header>
          <md-card-content>
            <h3 v-if="this.data['blood_pressure_diastolic'] == undefined && this.data['blood_pressure_systolic'] == undefined">No blood pressure data available</h3>
            <blood-pressure-chart :key="keyvalue" :width="370" :height="246" 
              :chart="[dates,data.blood_pressure_diastolic,data.blood_pressure_systolic]" 
              :thresholds="thresholds"
              v-if="this.data['blood_pressure_diastolic'] != undefined || this.data['blood_pressure_systolic'] != undefined"></blood-pressure-chart> 
          </md-card-content>
        </md-card>
      </div>
      <div class="md-layout-item md-xlarge-size-100 md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-33">
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title">Steps</h2>
          </md-card-header>
          <md-card-content>
            <h3 v-if="this.data['cnt_steps'] == undefined">No steps data available</h3>
            <steps-chart :key="keyvalue" :width="370" :height="246" :chart="[dates,data.cnt_steps]" :thresholds="thresholds" v-if="this.data['cnt_steps'] != undefined"></steps-chart>
          </md-card-content>
        </md-card>
      </div>
      <div class="md-layout-item md-xlarge-size-100 md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-33">
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title">Sleep</h2>
          </md-card-header>
          <md-card-content>
            <h3 v-if="this.data['sleep_light'] == undefined && this.data['sleep_rem'] == undefined && this.data['sleep_deep'] == undefined">No sleep data available</h3>
            <sleep-chart :key="keyvalue" :width="370" :height="246" :chart="[dates,data.sleep_light,data.sleep_rem,data.sleep_deep]" :thresholds="thresholds"
            v-if="this.data['sleep_light'] != undefined || this.data['sleep_rem'] != undefined || this.data['sleep_deep'] != undefined"></sleep-chart>
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
import DatePicker from '../components/Picker/DatePicker.vue';

export default {
  components: {
    BloodPressureChart,
    StepsChart,
    SleepChart,
    ThresholdForm,
    DatePicker
  },
  data() {
    return {
      data: {},
      dates: [],
      setThresholds: false,
      aauData: true,
      dataloaded: false,
      thresholds: {},
      thresholdIds: {},
      selectedFrom: null,
      selectedTo: null,
      keyvalue: 0,
      patient_id: '010101-1234',
      measurement_types: ['blood_pressure_diastolic','blood_pressure_systolic','cnt_steps','sleep_light','sleep_rem','sleep_deep'],
    };
  },
  methods: {
    toggleThresholdsForm() {
      this.updateCharts();
      this.setThresholds = !this.setThresholds;
    },
    async toggleAauData() {
      //this.aauData = !this.aauData;
      this.data = {};
      this.thresholds = {};
      this.thresholdIds = {};
      this.selectedFrom = null,
      this.selectedTo = null,
      this.dates = [];
      this.dataloaded = false;

      this.retrieveMeasurements();
      
      //this.updateCharts();
      await sleep(1000);
      this.dataloaded = true;
    },
    async changePatient(newPatient_id) {
      this.patient_id = newPatient_id;
      this.data = {};
      this.thresholds = {};
      this.thresholdIds = {};
      this.selectedFrom = null,
      this.selectedTo = null,
      this.dates = [];
      this.dataloaded = false;
      
      this.retrieveMeasurements();
      this.retrieveThresholds();
      await sleep(1000);
      this.dataloaded = true;
      console.log("updated to patient: "+newPatient_id);
    },
    updateCharts() {
      this.keyvalue = this.keyvalue+1;
    },
    newThreshold(newThreshold) {  
      for (var key in newThreshold) { 
        this.updateThreshold(key.substring(0, key.length-5),key.substr(key.length - 5),newThreshold[key])
      }

      console.log("Thresholds updated");
      this.updateCharts();
      this.toggleThresholdsForm();
    },
    updateThreshold(measurement_type,threshold_type,threshold_value) {
      var id = this.thresholdIds[measurement_type+threshold_type];
      if (!(id > 0)) {
        id = 0;
      }

      if (threshold_value == '') {
        threshold_value = null;
      }
      
      this.thresholds[measurement_type+threshold_type] = threshold_value;
      console.log('heeer')
      console.log(typeof threshold_value)
      
      var data = {
          id: id,
          patientid: this.patient_id,
          measurementtype: measurement_type,
          thresholdtype: threshold_type,
          thresholdvalue: threshold_value
        };
      ThresholdDataService.updateThreshold(data['id'],data)
        .then(response => {
          this.thresholdIds[measurement_type+threshold_type] = response.data.id;
        })
        .catch(e => {
          console.log(e);
        });
    },
    async updateTime(from,to) {
      this.selectedFrom = from;
      this.selectedTo = to;
      this.data = {};
      this.dates = [];
      const toPlus1 = new Date(to.getTime() + 86400000)
      for (var typ in (this.measurement_types)) {
        MeasurementDataService.getMeasurementsFromTo(this.patient_id,this.measurement_types[typ],from,toPlus1)
        .then(response => {
          response.data.map(m => this.pushMeasurementsIntoData(m));
        })
        .catch(e => {
          console.log(e);
        });
      }
      this.dataloaded = false;
      await sleep(1000);
      this.updateCharts();
      this.dataloaded = true;
    },
    retrieveMeasurements() {
      var typ;
      for (typ in (this.measurement_types)) {
        if (!this.aauData) {
          MeasurementDataService.get7Latest(this.patient_id,this.measurement_types[typ])
          .then(response => {
            if (response.data.length != 0) {
              response.data.map(m => this.pushMeasurementsIntoData(m));
            }
          })
          .catch(e => {
            console.log(e);
          });
        } else {
          MeasurementDataService.get7LatestAau("4005","2cf6c93d",this.measurement_types[typ])
          .then(response => {
            if (response.data.length != 0) {
              response.data.map(m => this.pushMeasurementsIntoData(m));
            }
          })
          .catch(e => {
            console.log(e);
          });
        }
      }
    },
    pushMeasurementsIntoData(m) {
      if (this.data[m.measurementtype]==null) {
        this.data[m.measurementtype] = []
      }
      this.data[m.measurementtype].push(m.measurementvalue);
      
      //special case for dates, need to be fixed someday
      if (m.measurementtype == "sleep_rem") {
        var date = new Date(m.datepost);
        this.dates.push(date.toDateString());
      }
    },
    retrieveThresholds() {
      ThresholdDataService.getPatientsThresholds(this.patient_id)
        .then(response => {
          //Insert thresholds if there is content
          if (response.status!=204) {
            response.data.map(t => this.insertThreshold(t)); 
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    insertThreshold(t) {
      this.thresholdIds[t.measurementtype+t.thresholdtype] = t.id;
      this.thresholds[t.measurementtype+t.thresholdtype] = t.thresholdvalue;
    },
    onResize(event) {
      this.updateCharts();
    }
  },
  async created() {
    this.retrieveMeasurements();
    this.retrieveThresholds();
    await sleep(1000);
    this.dataloaded = true;
  },
  mounted() {
    window.addEventListener("resize", this.onResize)
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.onResize)
  }
};

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}
</script>

