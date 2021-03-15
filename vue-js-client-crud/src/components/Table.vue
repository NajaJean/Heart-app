<template>
  <div class="list row">
    <div class="col-md-6">
      <h4>Daily Recordings List</h4>
      <ul class="list-group">
        <li class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(dailyrecording, index) in dailyrecordings"
          :key="index"
          @click="setActiveDailyRecording(dailyrecording, index)"
        >
          {{ dailyrecording.patient_ID }}
        </li>
      </ul>
    </div>
    <div class="col-md-6">
      <div v-if="currentDailyRecording">
        <h4>Daily Recording</h4>
        <div>
          <label><strong>Patient ID:</strong></label> {{ currentDailyRecording.patient_ID }}
        </div>
		<div>
          <label><strong>Light Sleep:</strong></label> {{ currentDailyRecording.sleep_light }}
        </div>
		<div>
          <label><strong>Deep Sleep:</strong></label> {{ currentDailyRecording.sleep_deep }}
        </div>
		<div>
          <label><strong>REM Sleep:</strong></label> {{ currentDailyRecording.sleep_rem }}
        </div>
		<div>
          <label><strong>Weight:</strong></label> {{ currentDailyRecording.weight }}
        </div>
        <div>
          <label><strong>Steps:</strong></label> {{ currentDailyRecording.cnt_steps }}
        </div>
        <div>
          <label><strong>Blood Pressure:</strong></label> {{ currentDailyRecording.blood_pressure }}
        </div>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Daily Recording...</p>
      </div>
    </div>
  </div>
</template>

<script>
import DailyRecordingDataService from "../services/DailyRecordingDataService";

export default {
  name: "dailyrecordings-list",
  data() {
    return {
      dailyrecordings: [],
      currentDailyRecording: null,
      currentIndex: -1,
      patient_ID: ""
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
    },

    refreshList() {
      this.retrieveDailyRecordings();
      this.currentDailyRecording = null;
      this.currentIndex = -1;
    },

    setActiveDailyRecording(dailyrecording, index) {
      this.currentDailyRecording = dailyrecording;
      this.currentIndex = index;
    },
  },
  mounted() {
    this.retrieveDailyRecordings();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>