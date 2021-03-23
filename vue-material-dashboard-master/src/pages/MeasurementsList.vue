<template>
  <div class="content">
    <div class="md-layout">
      <div
        class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100"
      >
        <md-card>
          <md-card-header data-background-color="blue">
            <h4 class="title">Daily Recordings</h4>
            <p class="category">Here is a subtitle for this table</p>
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