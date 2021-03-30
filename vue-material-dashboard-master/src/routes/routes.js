import DashboardLayout from "@/pages/Layout/DashboardLayout.vue";

import Dashboard from "@/pages/Dashboard.vue";
import ECGLive from "@/pages/ECGLive.vue";
import MeasurementsList from "@/pages/MeasurementsList.vue";
import Notifications from "@/pages/Notifications.vue";

const routes = [
  {
    path: "/",
    component: DashboardLayout,
    redirect: "/dashboard",
    children: [
      {
        path: "dashboard",
        name: "Dashboard",
        component: Dashboard
      },
      {
        path: "ecglive",
        name: "ECGLive",
        component: ECGLive
      },
      {
        path: "measurements",
        name: "Measurements",
        component: MeasurementsList
      },
      {
        path: "notifications",
        name: "Notifications",
        component: Notifications
      }
    ]
  }
];

export default routes;
