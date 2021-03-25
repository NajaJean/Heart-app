<template>
  <div>
    <md-table v-model="measurements" :table-header-color="tableHeaderColor">
      <md-table-row slot="md-table-row" slot-scope="{ item }">
        <md-table-cell md-label="Patient ID">{{ item.patient_id }}</md-table-cell>
        <md-table-cell md-label="Time">{{ item.date_post }}</md-table-cell>
        <md-table-cell md-label="Measurement Type">{{ item.measurement_type }}</md-table-cell>
        <md-table-cell md-label="Value">{{ item.measurement_value }}</md-table-cell>
      </md-table-row>
    </md-table>
  </div>
</template>

<script>
import MeasurementDataService from "../services/MeasurementDataService";

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
      measurements: [],
      selected: [],
      patient: '010101-1234'
    };
  },
  methods: {
    retrieveMeasurements() {
      MeasurementDataService.get7LatestMeasurements(this.patient)
        .then(response => {
          this.measurements = response.data;
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.retrieveMeasurements();
  }
};
</script>
