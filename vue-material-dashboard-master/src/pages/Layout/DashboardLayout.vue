<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <notifications></notifications>

    <side-bar
      :sidebar-item-color="sidebarBackground"
      :sidebar-background-image="sidebarBackgroundImage"
    >
      <sidebar-link to="/dashboard">
        <md-icon>dashboard</md-icon>
        <p>Dashboard</p>
      </sidebar-link>
      <sidebar-link to="/ecglive">
        <md-icon>favorite</md-icon>
        <p>ECG Live</p>
      </sidebar-link>
      <sidebar-link to="/measurements">
        <md-icon>notifications</md-icon>
        <p>Measurements</p>
      </sidebar-link>
        <!---
      <sidebar-link to="/notifications">
        <md-icon>alarm</md-icon>
        <p>Notifications</p>
      </sidebar-link> -->
    </side-bar>

    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content> </dashboard-content>
    </div>
  </div>
</template>

<script>
import TopNavbar from "./TopNavbar.vue";
import DashboardContent from "./Content.vue";

export default {
  components: {
    TopNavbar,
    DashboardContent
  },
  data() {
    return {
      sidebarBackground: "green",
      sidebarBackgroundImage: require("@/assets/img/HeartAppSideBar.png")
    };
  }
};
</script>
