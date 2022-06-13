<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-xlarge-size-100 md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-33">
        <h4>{{this.mock ? "This data is previously recorded data." : "This data is live data."}}<br/>{{this.mock ? "To get real time ECG, press the 'Get Live ECG' button." : "If no sensor is attached, the graph is empty."}}<br/>{{this.mock ? "" : "Press 'Get Recorded ECG' to see previously recorded data."}}</h4>
        <md-field class="md-layout-item md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-33">
          <label>CPR</label>
          <md-input name="patientID" v-model="patient_id" disabled></md-input>
          <md-button name="mockButton" class="md-dense md-raised md-info" @click="toggleMock()">{{this.mock ? "Get Live ECG" : "Get Recorded ECG"}}</md-button>
        </md-field>
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title" font-weight="bold">{{this.mock ? "Recorded ECG" : "Live ECG"}}</h2>
          </md-card-header>
          <md-card-content>
            <h2 :key="heartRate" class="title" font-weight="bold" text-align="center">Heart Rate: {{this.heartRate}}</h2>
            <e-c-g-chart v-if="!this.mock" :key="mock" name='ecg-chart' :width="370" :height="246"></e-c-g-chart> 
            <old-e-c-g-chart v-if="this.mock" :key="mock" name='ecg-chart' :width="370" :height="246"></old-e-c-g-chart> 
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>
// <md-card-content>
//             <h2 :key="heartRate" class="title" font-weight="bold" text-align="center">Heart Rate: {{this.heartRate}}</h2>
//             <e-c-g-chart v-if="!this.mock" :key="mock" name='ecg-chart' :width="370" :height="246"></e-c-g-chart> 
//             <mocked-e-c-g-chart v-if="this.mock" :key="mock" name='ecg-chart' :width="370" :height="246"></mocked-e-c-g-chart> 
//           </md-card-content>


import ECGChart from '../components/Charts/ECGChart.vue';
import OldECGChart from '../components/Charts/OldECGChart.vue';
//import MockedECGChart from '../components/Charts/MockedECGChart.vue';
import MeasurementDataService from "../services/MeasurementDataService";
import MockedData from '../components/Charts/MockedData';

export default {
  components: {
    ECGChart,
    OldECGChart,
    //MockedECGChart
  },
  data() {
    return {
      heartRate: null,
      patient_id: '1',
      mock: false,
    };
  },
  created() {
    this.interval = setInterval(() => this.getHeartRate(), 500);
  },
  methods: {
    toggleMock() {
      this.mock = !this.mock;
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
      await sleep(2000);
      this.dataloaded = true;
      console.log("updated to patient: "+newPatient_id);
    },
    getHeartRate() {
      if (this.mock) {
        const count = OldECGChart.fetchCount();
        
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
