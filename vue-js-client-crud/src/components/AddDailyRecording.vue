<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="patient_ID">Patient ID</label>
        <input type="text" class="form-control" id="patient_ID" required v-model="dailyrecording.patient_ID" name="patient_ID"
        />
      </div>

      <div class="form-group">
        <label for="sleep_light">Sleep Light</label>
        <input class="form-control" id="sleep_light" required v-model="dailyrecording.sleep_light" name="sleep_light"
        />
      </div>
	<div class="form-group">
	<label for="sleep_deep">Sleep Deep</label>
	<input class="form-control" id="sleep_deep" required v-model="dailyrecording.sleep_deep" name="sleep_deep"
	/>
	</div>
	<div class="form-group">
	<label for="sleep_rem">Sleep Rem</label>
	<input
		class="form-control"
		id="sleep_rem"
		required
		v-model="dailyrecording.sleep_rem"
		name="sleep_rem"
	/>
	</div>
	<div class="form-group">
	<label for="weight">Weight</label>
	<input
		class="form-control"
		id="weight"
		required
		v-model="dailyrecording.weight"
		name="weight"
	/>
	</div>
	<div class="form-group">
	<label for="cnt_steps">Steps</label>
	<input
		class="form-control"
		id="cnt_steps"
		required
		v-model="dailyrecording.cnt_steps"
		name="cnt_steps"
	/>
	<div class="form-group">
        <label for="blood_pressure">Blood Pressure</label>
        <input type="text" class="form-control" id="blood_pressure" required v-model="dailyrecording.blood_pressure" name="blood_pressure"
        />
      </div>
	</div>

	
    <button @click="saveDailyRecording" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newDailyRecording">Add</button>
    </div>
  </div>
</template>

<script>
import DailyRecordingDataService from "../services/DailyRecordingDataService";

export default {
  name: "add-dailyrecording",
  data() {
    return {
      dailyrecording: {
		id: null,
		patient_ID: "",
		sleep_light: 0,
		sleep_deep: 0,
		sleep_rem: 0,
		weight: 0,
		cnt_steps: 0,
		blood_pressure: ""
      },
      submitted: false
    };
  },
  methods: {
    saveDailyRecording() {
      var data = {
        patient_ID: this.dailyrecording.patient_ID,
		sleep_light: this.dailyrecording.sleep_light,
		sleep_deep: this.dailyrecording.sleep_deep,
		sleep_rem: this.dailyrecording.sleep_rem,
		weight: this.dailyrecording.weight,
		cnt_steps: this.dailyrecording.cnt_steps,
		blood_pressure: this.dailyrecording.blood_pressure
      };

      DailyRecordingDataService.create(data)
        .then(response => {
          this.dailyrecording.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newDailyRecording() {
      this.submitted = false;
      this.dailyrecording = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>