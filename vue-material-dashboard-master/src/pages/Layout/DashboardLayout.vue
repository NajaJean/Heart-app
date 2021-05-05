<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
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
      <sidebar-link to="/" class="active-pro">
        <md-icon>logout</md-icon>
        <p>Log Out</p>
      </sidebar-link>
    </side-bar>

    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content> </dashboard-content>
      <div class="text-center">
        Made with <i class="fa fa-heart heart"></i> by Naja Jean Larsen & Mathilde Tannebæk
      </div>
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
