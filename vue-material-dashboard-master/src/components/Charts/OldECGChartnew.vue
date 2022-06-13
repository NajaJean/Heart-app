<template>
  <div>
    <canvas id="OldECGChartCanvas"></canvas>
  </div>
</template>

<script>
import { Chart, registerables } from 'chart.js';
import 'chartjs-adapter-moment'; // or another adapter to avoid moment
Chart.register(...registerables);
import oldECGData from './Data/OldECGData';
import ChartStreaming from 'chartjs-plugin-streaming';
import 'chartjs-adapter-date-fns';

Chart.register(ChartStreaming);

export default {
  name: 'oldECGChart',
  data() {
    return {
      oldECGData: oldECGData
    }
  },
  mounted() {
    const ctx = document.getElementById('OldECGChartCanvas');
    new Chart(ctx, this.oldECGData);
  }
}
</script> 