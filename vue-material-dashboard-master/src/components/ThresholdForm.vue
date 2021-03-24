<template>
    <form>
    <md-card>
      <md-card-header data-background-color="green">
        <h4 class="title">Set Thresholds</h4>
      </md-card-header>

      <md-card-content>
        <div class="md-layout">
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Systolic lower limit</label>
              <md-input v-model="blood_pressure_systoliclow" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Systolic upper limit</label>
              <md-input v-model="blood_pressure_systolicup" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Diastolic lower limit</label>
              <md-input v-model="blood_pressure_diastoliclow" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Diastolic upper limit</label>
              <md-input v-model="blood_pressure_diastolicup" type="number"></md-input>
            </md-field>
          </div>

          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Steps lower limit</label>
              <md-input v-model="cnt_stepslow" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Steps upper limit</label>
              <md-input v-model="cnt_stepsup" type="number"></md-input>
            </md-field>
          </div>

          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep light lower limit</label>
              <md-input v-model="sleep_lightlow" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep light upper limit</label>
              <md-input v-model="sleep_lightup" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep REM lower limit</label>
              <md-input v-model="sleep_remlow" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep REM upper limit</label>
              <md-input v-model="sleep_remup" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep deep lower limit</label>
              <md-input v-model="sleep_deeplow" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep deep upper limit</label>
              <md-input v-model="sleep_deepup" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-size-100 text-right">
            <md-button class="md-raised md-info" @click="submitThresholds()">Update Thresholds</md-button>
          </div>
        </div>
      </md-card-content>
    </md-card>
  </form>
</template>

<script>

export default ({
    props:['thresholds'],
    data: function() { 
      /*var data = {};
      for (var measurement_type in this.thresholds) {
        if (this.thresholds.hasOwnProperty(measurement_type)) {
          data[this.thresholds.measurement_type+"low"] = this.thresholds.measurement_type[0];
          data[this.thresholds.measurement_type+"up"] = this.thresholds.measurement_type[1];
        }
      }
      console.log(data);
      return data;*/
      return {
        blood_pressure_systoliclow: this.thresholds.blood_pressure_systolic[0],
        blood_pressure_systolicup: this.thresholds.blood_pressure_systolic[1],
        blood_pressure_diastoliclow: this.thresholds.blood_pressure_diastolic[0],
        blood_pressure_diastolicup: this.thresholds.blood_pressure_diastolic[1],

        cnt_stepslow: this.thresholds.cnt_steps[0],
        cnt_stepsup: this.thresholds.cnt_steps[1],

        sleep_lightlow: this.thresholds.sleep_light[0],
        sleep_lightup: this.thresholds.sleep_light[1],
        sleep_remlow: this.thresholds.sleep_rem[0],
        sleep_remup: this.thresholds.sleep_rem[1],
        sleep_deeplow: this.thresholds.sleep_deep[0],
        sleep_deepup: this.thresholds.sleep_deep[1],
      };
    },
    methods: {
      submitThresholds() {
        var newThreshold = {};
        newThreshold["blood_pressure_systolic"] = [this.blood_pressure_systoliclow,this.blood_pressure_systolicup];
        newThreshold["blood_pressure_diastolic"] = [this.blood_pressure_diastoliclow,this.blood_pressure_diastolicup];
        newThreshold["cnt_steps"] = [this.cnt_stepslow,this.cnt_stepsup];
        newThreshold["sleep_light"] = [this.sleep_lightlow,this.sleep_lightup];
        newThreshold["sleep_rem"] = [this.sleep_remlow,this.sleep_remup];
        newThreshold["sleep_deep"] = [this.sleep_deeplow,this.sleep_deepup];

        this.$emit('new-threshold', newThreshold)
      }
    }
})
</script>
