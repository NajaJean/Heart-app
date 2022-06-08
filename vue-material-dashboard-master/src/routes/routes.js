import DashboardLayout from "@/pages/Layout/DashboardLayout.vue";
import Dashboard from "@/pages/Dashboard.vue";
import ECGLive from "@/pages/ECGLive.vue";
import About from "@/pages/About.vue";
import MeasurementsList from "@/pages/MeasurementsList.vue";
import LogIn from "@/pages/Login.vue";

const routes = [
  {
    path: "/",
    redirect: "/login"
  },
  {
    path: "/login",
    component: LogIn
  },
  {
    path: "/dashboardLayout",
    component: DashboardLayout,
    redirect: "/dashboard",
    children: [
      {
        path: "/dashboard",
        name: "Dashboard",
        component: Dashboard
      },
      {
        path: "/ecglive",
        name: "ECG Live",
        component: ECGLive
      },
      {
        path: "/about",
        name: "About",
        component: About
      },
      {
        path: "/measurements",
        name: "Measurements",
        component: MeasurementsList
      }
    ]
  }
];
export default routes;
