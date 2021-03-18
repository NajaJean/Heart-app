<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-33"
      >
        <line-chart :width="370" :height="246" :chart="chartdata"></line-chart> 
      </div>
    </div>
  </div>
</template>




<script>
import DailyRecordingDataService from "../services/DailyRecordingDataService";
import LineChart from "../components/LineChart";

export default {
  components: {
    LineChart
  },
  data() {
    return {
      chartdata: []
    };
  },
  methods: {
    retrieveDailyRecordings() {
      DailyRecordingDataService.getAll()
        .then(response => {
          var dates = [];
          var bps = [];
          var bpd = [];
          response.data.map(dr => dates.push(dr.date_post.substring(0,3)));
          response.data.map(dr => bps.push(dr.blood_pressure_systolic));
          response.data.map(dr => bpd.push(dr.blood_pressure_diastolic));

          this.chartdata = [dates,bpd,bps];
          console.log([dates,bps,bpd]);
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
