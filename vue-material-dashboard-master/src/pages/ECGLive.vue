<template>
  <div class="content">
    <div class="md-layout">
      <div class="md-layout-item md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-33">
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title" font-weight="bold">ECG During the Week</h2>
          </md-card-header>
          <md-card-content>
            <e-c-g-chart name='ecg-chart' :width="370" :height="246"></e-c-g-chart> 
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>
import ECGChart from '../components/Charts/ECGChart.vue';

export default {
  components: {
    ECGChart
  },
};

</script>