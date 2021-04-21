<template>
  <md-field class="md-layout-item md-large-size-100 md-medium-size-100 md-xsmall-size-100 md-size-33">
    <md-datepicker name="from" v-model="selectedFrom" md-immediately :md-disabled-dates="disabledDatesFrom"><label>From:</label></md-datepicker>
    <md-datepicker name="to" v-model="selectedTo" md-immediately :md-disabled-dates="disabledDatesTo"><label>To:</label></md-datepicker>
    <md-button name="updateDate" class="md-dense md-raised md-info" :disabled="selectedFrom == null || selectedTo == null" @click="updateTime()">Update time</md-button>
</md-field>
</template>
<script>
// Back and forth arrows
//<md-button class="md-icon-button md-dense"><md-icon>arrow_back_ios</md-icon></md-button>
//<md-button class="md-icon-button md-dense"><md-icon>arrow_forward_ios</md-icon></md-button>
export default {
  props:['selectedfrom','selectedto'],
    name: "date-picker",
    data: function() {return {
      selectedFrom: this.selectedfrom,
      selectedTo: this.selectedto,
      disabledDatesFrom: date => { 
        if (this.selectedTo != null) { return date > new Date() || date > this.selectedTo }
        return date > new Date() },
      disabledDatesTo: date => {
        if (this.selectedFrom != null) { return date > new Date() || date < this.selectedFrom }
        return date > new Date()
      }
    };},
    methods: {
      updateTime() {
        this.$emit('update-time', this.selectedFrom, this.selectedTo)
      }

    }
};
</script>
