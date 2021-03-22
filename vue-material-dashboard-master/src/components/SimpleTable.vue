<template>
  <div>
    <md-table v-model="dailyrecordings" :table-header-color="tableHeaderColor">
      <md-table-row slot="md-table-row" slot-scope="{ item }">
        <md-table-cell md-label="Patient ID">{{ item.patient_id }}</md-table-cell>
        <md-table-cell md-label="Time">{{ item.date_post }}</md-table-cell>
        <md-table-cell md-label="Blood Pressure">{{ item.blood_pressure_systolic }}/{{ item.blood_pressure_diastolic }} mmHg</md-table-cell>
        <md-table-cell md-label="Weight">{{ item.weight }}</md-table-cell>
        <md-table-cell md-label="Steps">{{ item.cnt_steps }}</md-table-cell>
      </md-table-row>
    </md-table>
  </div>
</template>

<script>
import DailyRecordingDataService from "../services/DailyRecordingDataService";

export default {
  name: "simple-table",
  props: {
    tableHeaderColor: {
      type: String,
      default: ""
    }
  },
  data() {
    return {
      dailyrecordings: [],
      selected: []
    };
  },
  methods: {
    retrieveDailyRecordings() {
      DailyRecordingDataService.getAll()
        .then(response => {
          this.dailyrecordings = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.retrieveDailyRecordings();
  }
};
</script>
