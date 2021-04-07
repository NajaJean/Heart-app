<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-100"
      >
        <md-card>
          <md-card-header data-background-color="blue">
            <h2 class="title">Latest Measurements</h2>
          </md-card-header>
          <md-card-content>
            <measurements-table table-header-color="blue"></measurements-table>
          </md-card-content>
        </md-card>
      </div>
    </div>
  </div>
</template>

<script>
import MeasurementsTable from "../components/MeasurementsTable";

export default {
  components: {
    MeasurementsTable
  }
};
</script>