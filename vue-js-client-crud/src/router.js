import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/dailyrecordings",
      name: "dailyrecordings",
      component: () => import("./components/Table")
    },
    {
      path: "/adddailyrecording",
      name: "adddailyrecording",
      component: () => import("./components/AddDailyRecording")
    },
    {
      path: "/charts",
      name: "charts",
      component: () => import("./components/Charts")
    }
  ]
});