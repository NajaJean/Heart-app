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
              <md-input name="bloodSysLower" v-model="form.blood_pressure_systoliclower" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Systolic upper limit</label>
              <md-input name="bloodSysUpper" v-model="form.blood_pressure_systolicupper" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Diastolic lower limit</label>
              <md-input v-model="form.blood_pressure_diastoliclower" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Diastolic upper limit</label>
              <md-input v-model="form.blood_pressure_diastolicupper" type="number"></md-input>
            </md-field>
          </div>

          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Steps lower limit</label>
              <md-input v-model="form.cnt_stepslower" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Steps upper limit</label>
              <md-input v-model="form.cnt_stepsupper" type="number"></md-input>
            </md-field>
          </div>

          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep light lower limit</label>
              <md-input v-model="form.sleep_lightlower" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep light upper limit</label>
              <md-input v-model="form.sleep_lightupper" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep REM lower limit</label>
              <md-input v-model="form.sleep_remlower" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep REM upper limit</label>
              <md-input v-model="form.sleep_remupper" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep deep lower limit</label>
              <md-input v-model="form.sleep_deeplower" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep deep upper limit</label>
              <md-input v-model="form.sleep_deepupper" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-size-100 text-right">
            <md-button name="updateThreshold" class="md-raised md-info" @click="submitThresholds()">Update Thresholds</md-button>
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
      return {
        form: this.getThresholdData(),
        defaultForm: {}
      };
    },
    methods: {
      getThresholdData() {
        var thres = {};
        for (var key in this.thresholds) {
          thres[key] = this.thresholds[key];
        }
        return thres;
      },
      submitThresholds() {
        var newThreshold = {};
        var changedFields = Object.keys(this.form).filter(field => this.form[field] !== this.defaultForm[field])

        changedFields.map(field => newThreshold[field] = this.form[field]);

        console.log(newThreshold);

        this.$emit('new-threshold', newThreshold)
      }
    },
    created() {
      this.defaultForm = this.getThresholdData();
    }
})
</script>
