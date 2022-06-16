<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-xlarge-size-100 md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-33">
        <div class="md-layout">
          <div class="md-layout-item" style="text-align: right">
            <md-switch v-model="liveData">Live data</md-switch>
          </div>
        </div>
        <md-field class="md-layout-item md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-33">
          <label>CPR</label>
          <md-input name="patientID" v-model="patient_id"></md-input>
          <md-button name="changePatient" class="md-dense md-raised md-info" @click="changePatient(patient_id)">Change Patient</md-button>
        </md-field>
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title" font-weight="bold">{{this.liveData ? "Live ECG" : "Recorded ECG"}}</h2>
          </md-card-header>
          <md-card-content>
            <div class="md-layout">
              <div class="md-layout-item">
                <h2 :key="heartRate" class="title" font-weight="bold" text-align="center">Heart Rate: {{this.heartRate}}</h2>
              </div>
              <div class="md-layout-item" style="text-align: right">
                  <span v-if="this.ECGclassification == null">No ML is available </span>
                  <span v-if="this.ECGclassification == 0">Normal </span>
                  <span v-if="this.ECGclassification == 1">Irregular </span>

                  <span class="dot" style="background-color:crimson;" v-if="this.ECGclassification == 1"></span>
                  <span class="dot" style="background-color:darkgreen" v-if="this.ECGclassification == 0"></span>
                  <span class="dot" style="background-color:blanchedalmond" v-if="this.ECGclassification == null"></span>
              </div>
            </div>
            <e-c-g-chartnew v-if="this.liveData" :key="liveData" name='ecg-chart' :width="370" :height="246"></e-c-g-chartnew> 
            <old-e-c-g-chartnew v-if="!this.liveData" :key="liveData" name='ecg-chart'></old-e-c-g-chartnew> 
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>


//import ECGChart from '../components/Charts/ECGChart.vue';
import ECGChartnew from '../components/Charts/ECGChartnew.vue';
//import OldECGChart from '../components/Charts/OldECGChart.vue';
import OldECGChartnew from '../components/Charts/OldECGChartnew.vue';
//import MockedECGChart from '../components/Charts/MockedECGChart.vue';
import MeasurementDataService from "../services/MeasurementDataService";
import ECGClassificationDataService from '../services/ECGClassificationDataService';
import MockedData from '../components/Charts/MockedData';
import 'chartjs-plugin-streaming';
import 'chartjs-adapter-moment';
import { Chart, registerables } from 'chart.js';
import 'chartjs-adapter-moment'; // or another adapter to avoid moment
import 'chartjs-plugin-annotation';
Chart.register(...registerables);

export default {
  components: {
    //ECGChart,
    //OldECGChart,
    OldECGChartnew,
    //MockedECGChart
    ECGChartnew
  },
  data() {
    return {
      heartRate: null,
      patient_id: '1',
      liveData: true,
      ECGclassification: null,
      latestFetch: new Date(),
    };
  },
  created() {
    this.interval = setInterval(() => {this.getHeartRate(); this.getECGClassification()}, 500);
  },
  methods: {
    
    async changePatient(newPatient_id) {
      this.patient_id = newPatient_id;
      this.data = {};
      this.thresholds = {};
      this.thresholdIds = {};
      this.selectedFrom = null,
      this.selectedTo = null,
      this.dates = [];
      this.dataloaded = false;
      this.ECGclassification = null;
      
      this.retrieveMeasurements();
      this.retrieveThresholds();
      await sleep(2000);
      this.dataloaded = true;
      console.log("updated to patient: "+newPatient_id);
    },
    getECGClassification() {
      if (!this.liveData) {
        this.ECGclassification = Math.round(Math.random())
      } else {
        ECGClassificationDataService.getLatestECGClassification(this.patient_id).then(response => {
          const tstamp = new Date(response.timestamp);
          if (tstamp > this.latestFetch) {
            this.latestFetch = tstamp;
            const newClassification = response.data.mldata;
            this.ECGclassification = newClassification;
          }
        }).catch(e => {
          console.log(e);
        });
      }
    },
    getHeartRate() {
      if (!this.liveData) {
        const count = OldECGChartnew.fetchCount();
        console.log(count)
        
        MeasurementDataService.getOldECG(this.patient_id).then(response => {
          const newrate = response.data[count].measurementvalue[response.data[count].measurementvalue.length-1];
          if (this.heartRate != newrate) {
            this.heartRate = newrate;
          }     
        })
        .catch(e => {
          console.log(e);
        });

        //this.heartRate = 84 + Math.floor((Math.random() * 10) + 1);;
      } else {
        MeasurementDataService.getLatestECG(this.patient_id)
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
  }
};
</script>

<style>
  @import '../assets/scss/extracss.css';
</style>
