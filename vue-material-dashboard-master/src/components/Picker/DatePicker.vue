<template>
<div class="md-layout md-size-100">

    <div class="md-layout-item md-xsmall-size-100 md-size-15">
      <md-datepicker name="from" v-model="selectedFrom" md-immediately :md-disabled-dates="disabledDatesFrom"><label>From:</label></md-datepicker>
    </div>
    <div class="md-layout-item md-xsmall-size-100 md-size-15">
      <md-datepicker name="to" v-model="selectedTo" md-immediately :md-disabled-dates="disabledDatesTo"><label>To:</label></md-datepicker>
    </div>
    <div class="md-layout-item md-xsmall-size-20 md-size-5" >
      <md-button name="updateDate" class="md-dense md-raised md-info" :disabled="selectedFrom == null || selectedTo == null" @click="updateTime()" style="margin-top: 23px;">Update</md-button>
    </div>
  </div>
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
        //console.log(this.selectedTo);
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
