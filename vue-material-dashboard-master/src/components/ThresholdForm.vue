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
              <md-input v-model="syslow" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Systolic upper limit</label>
              <md-input v-model="sysup" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Diastolic lower limit</label>
              <md-input v-model="dialow" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Diastolic upper limit</label>
              <md-input v-model="diaup" type="number"></md-input>
            </md-field>
          </div>

          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Steps lower limit</label>
              <md-input v-model="steplow" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Steps upper limit</label>
              <md-input v-model="stepup" type="number"></md-input>
            </md-field>
          </div>

          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep light lower limit</label>
              <md-input v-model="lightlow" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep light upper limit</label>
              <md-input v-model="lightup" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep REM lower limit</label>
              <md-input v-model="remlow" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep REM upper limit</label>
              <md-input v-model="remup" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep deep lower limit</label>
              <md-input v-model="deeplow" type="number"></md-input>
            </md-field>
          </div>
          <div class="md-layout-item md-small-size-100 md-size-50">
            <md-field>
              <label>Sleep deep upper limit</label>
              <md-input v-model="deepup" type="number"></md-input>
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
    data: function() { return {
      syslow: this.thresholds[0],
      sysup: this.thresholds[1],
      dialow: this.thresholds[2],
      diaup: this.thresholds[3],
      steplow: this.thresholds[4],
      stepup: this.thresholds[5],
      lightlow: this.thresholds[6],
      lightup: this.thresholds[7],
      remlow: this.thresholds[8],
      remup: this.thresholds[9],
      deeplow: this.thresholds[10],
      deepup: this.thresholds[11],
    }},
    methods: {
        submitThresholds() {
            const newThreshold = [
                parseInt(this.syslow),
                parseInt(this.sysup),
                parseInt(this.dialow),
                parseInt(this.diaup),
                parseInt(this.steplow),
                parseInt(this.stepup),
                parseInt(this.lightlow),
                parseInt(this.lightup),
                parseInt(this.remlow),
                parseInt(this.remup),
                parseInt(this.deeplow),
                parseInt(this.deepup),
            ]
            this.$emit('new-threshold', newThreshold)
        }
    }
})
</script>
