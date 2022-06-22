<template>
  <div class="content">
    <div class="md-layout" v-if="!this.dataloaded">
      <h1>Loading ...</h1>
    </div>
    <div class="md-layout" v-if="this.dataloaded">
      <div class="md-layout-item md-size-100" style="text-align: right; margin-bottom: -15px;">
        <md-switch v-model="aauData" @change="toggleAauData()" class="md-primary">Live data</md-switch>
      </div>
      
      <div class="md-layout-item md-xsmall-size-80 md-size-30" v-if="!aauData"> 
        <md-field >
          <label>CPR</label>
          <md-input name="patientID" v-model="patient_id"></md-input>
        </md-field>
      </div>
      <div class="md-layout-item md-xsmall-size-20 md-size-5" v-if="!aauData" style="margin-top: 15px;"> 
        <md-button name="changePatient" class="md-dense md-raised md-info" @click="changePatient(subject_id,'')">Submit</md-button>
      </div>
      <div class="md-layout-item md-xsmall-size-80 md-size-95"> 
      </div>

      <div class="md-layout-item md-xsmall-size-100 md-size-30" v-if="aauData"> 
        <md-field>
          <label>Subject ID</label>
          <md-input name="subjectID" v-model="subject_id"></md-input>
        </md-field>
      </div>
      <div class="md-layout-item md-large-size-75 md-medium-size-75 md-xsmall-size-0 md-size-60"> 
      </div>
      <div class="md-layout-item md-xsmall-size-80 md-size-30" v-if="aauData"> 
        <md-field >
          <label>Password</label>
          <md-input name="pwd" v-model="pwd" type="password"></md-input>
        </md-field>
      </div>
      <div class="md-layout-item md-xsmall-size-20 md-size-5" v-if="aauData" style="margin-top: 15px;"> 
        <md-button name="changePatient" class="md-dense md-raised md-info" @click="changePatient(subject_id,pwd)">Submit</md-button>
      </div>
      <div class="md-layout-item md-xsmall-size-80 md-size-95"> 
      </div>
      
      <date-picker :key="keyvalue" :selectedfrom="selectedFrom" :selectedto="selectedTo" @update-time="updateTime"></date-picker>

      
      <div class="md-layout-item md-size-100" style="text-align: right; margin-bottom: 5px;">
        <md-button name="thresholdButton" class="md-dense md-raised md-info" @click="toggleThresholdsForm()">{{this.setThresholds ? "Close thresholds" : "Set Thresholds"}}</md-button>
      </div>
        
       
      
      <div class="md-layout-item md-xlarge-size-100 md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-33">
        <threshold-form :key="keyvalue" v-if="this.setThresholds" :thresholds="thresholds" @new-threshold="newThreshold" @delete-threshold="deleteThreshold"></threshold-form>
      </div>
      <div class="md-layout-item md-xlarge-size-100 md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-33">
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title" font-weight="bold">Blood Pressure</h2>
          </md-card-header>
          <md-card-content>
            <h3 v-if="this.data['blood_pressure_diastolic'] == undefined && this.data['blood_pressure_systolic'] == undefined">No blood pressure data available</h3>
            <blood-pressure-chart :key="keyvalue" :width="370" :height="246" 
              :chart="[dates.blood_pressure_diastolic,data.blood_pressure_diastolic,data.blood_pressure_systolic]" 
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
            <steps-chart :key="keyvalue" :width="370" :height="246" :chart="[dates.cnt_steps,data.cnt_steps]" :thresholds="thresholds" v-if="this.data['cnt_steps'] != undefined"></steps-chart>
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
            <sleep-chart :key="keyvalue" :width="370" :height="246" :chart="[dates.sleep_light,data.sleep_light,data.sleep_rem,data.sleep_deep,data.totalsleep]" :thresholds="thresholds"
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
      dates: {},
      setThresholds: false,
      aauData: true,
      dataloaded: false,
      thresholds: {},
      thresholdIds: {},
      selectedFrom: null,
      selectedTo: null,
      keyvalue: 0,
      patient_id: '010101-1234',
      subject_id: '4005',
      pwd: '2cf6c93d',
      measurement_types: ['blood_pressure_diastolic','blood_pressure_systolic','cnt_steps','sleep_light','sleep_rem','sleep_deep'],
    };
  },
  methods: {
    toggleThresholdsForm() {
      this.updateCharts();
      this.setThresholds = !this.setThresholds;
    },
    async toggleAauData() {
      this.data = {};
      this.thresholds = {};
      this.thresholdIds = {};
      this.selectedFrom = null,
      this.selectedTo = null,
      this.dates = {};
      this.dataloaded = false;

      await this.retrieveMeasurements();
      this.dataloaded = true;
    },
    async changePatient(newPatient_id, newpwd) {
      if (this.aauData) {
        this.subject_id = newPatient_id;
        this.pwd = newpwd;
      } else {
        this.patient_id = newPatient_id;
      }

      this.data = {};
      this.thresholds = {};
      this.thresholdIds = {};
      this.selectedFrom = null,
      this.selectedTo = null,
      this.dates = {};
      this.dataloaded = false;
      
      await this.retrieveMeasurements();
      this.retrieveThresholds();
      this.dataloaded = true;
      console.log("updated to patient: "+newPatient_id);
    },
    updateCharts() {
      this.keyvalue = this.keyvalue+1;
    },
    newThreshold(newThreshold) {  
      for (var key in newThreshold) { 
        this.updateThreshold(key.substring(0, key.length-5),key.substring(key.length - 5),newThreshold[key])
      }
      console.log("Thresholds updated");
      this.updateCharts();
      this.toggleThresholdsForm();
    },
    deleteThreshold(threshold) {
      var id = this.thresholdIds[threshold.measurement_type+threshold.threshold_type];
      if (id) {
        ThresholdDataService.deleteThreshold(id)
        .then(response => {
          console.log(response)
          if (response.status == 204) {
            delete this.thresholdIds[threshold.measurement_type+threshold.threshold_type];
            delete this.thresholds[threshold.measurement_type+threshold.threshold_type];
            console.log('Deleted: '+threshold.toString())            
          }
          console.log('Deleted (not 204): '+threshold.toString())  
        })
        .catch(e => {
          console.log(e);
        });
      }
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
      this.dates = {}; 
      this.dataloaded = false;

      const toPlus1 = new Date(to.getTime() + 86400000);
      for (var typ in (this.measurement_types)) {
        if (this.aauData) {
          MeasurementDataService.getAauMeasurementsFromTo(this.subject_id,this.pwd,this.measurement_types[typ],from,toPlus1)
          .then(response => {
            response.data.map(m => {
              this.pushMeasurementsIntoData(m);
              this.pushDateIntoDates(m.measurementtype, m.datepost);
            });
          })
          .catch(e => {
            console.log(e);
          });
        } else {
          MeasurementDataService.getMeasurementsFromTo(this.patient_id,this.measurement_types[typ],from,to)
          .then(response => {
            response.data.slice(0).reverse().map(m => {
              this.pushMeasurementsIntoData(m);
              this.pushDateIntoDates(m.measurementtype, m.datepost);
            });
          })
          .catch(e => {
            console.log(e);
          });
        }
        await sleep(300);
      }
      this.updateCharts();
      this.dataloaded = true;
    },
    async retrieveMeasurements() {
      var typ;
      for (typ in (this.measurement_types)) {
        if (!this.aauData) {
          MeasurementDataService.get7Latest(this.patient_id,this.measurement_types[typ])
          .then(response => {
            if (response.data.length != 0) {
              response.data.slice(0).reverse().map(m => {
                this.pushMeasurementsIntoData(m);
                this.pushDateIntoDates(m.measurementtype, m.datepost);
              });
            }
          })
          .catch(e => {
            console.log(e);
          });
        } else {
          MeasurementDataService.get7LatestAau(this.subject_id,this.pwd,this.measurement_types[typ])
          .then(response => {
            if (response.data.length != 0) {
              response.data.map(m => {
                this.pushMeasurementsIntoData(m);
                this.pushDateIntoDates(m.measurementtype, m.datepost);
              });
            }
          })
          .catch(e => {
            console.log(e);
          });
        }
        await sleep(300);
      };

      this.addTotalSleep();
    },
    addTotalSleep() {
      //'sleep_light','sleep_rem','sleep_deep'
      const light = this.data['sleep_light'];
      const rem = this.data['sleep_rem'];
      const deep = this.data['sleep_deep']
      var totals = [];

      for (let i = 0; i < light.length; i++) {
        const total = Number(light[i]) + Number(rem[i]) + Number(deep[i]);
        totals.push(total.toString());
      }

      this.data['totalsleep'] = totals;
    },
    pushMeasurementsIntoData(m) {
      if (this.data[m.measurementtype]==null) {
        this.data[m.measurementtype] = []
      }
      this.data[m.measurementtype].push(m.measurementvalue);
    },
    pushDateIntoDates(dateType, date) {
      if (this.dates[dateType]==null) {
        this.dates[dateType] = []
      }
      this.dates[dateType].push((new Date(date)).toDateString());
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
    await this.retrieveMeasurements();
    this.retrieveThresholds();
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

<style id="xyz">

md-switch .md-thumb {
 background-color: #FF0000 !important;
}
md-switch .md-bar {
 background-color: #550000 !important; 
}
md-switch.md-checked:not([disabled]) .md-bar {
 background-color: #005500 !important; 
}
md-switch.md-checked:not([disabled]) .md-thumb {
 background-color: #00FF00 !important;
}

</style>