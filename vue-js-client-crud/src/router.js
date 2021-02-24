import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      alias: "/tutorials",
      name: "tutorials",
      component: () => import("./components/TutorialsList")
    },
    {
      path: "/dailyrecordings",
      name: "dailyrecordings",
      component: () => import("./components/Table")
    },
    {
      path: "/tutorials/:id",
      name: "tutorial-details",
      component: () => import("./components/Tutorial")
    },
    {
      path: "/add",
      name: "add",
      component: () => import("./components/AddTutorial")
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