import DashboardLayout from "@/pages/Layout/DashboardLayout.vue";
import Dashboard from "@/pages/Dashboard.vue";
import ECGLive from "@/pages/ECGLive.vue";
import MeasurementsList from "@/pages/MeasurementsList.vue";
import Notifications from "@/pages/Notifications.vue";
import LogIn from "@/pages/LogIn.vue";

const routes = [
    {
        path: "/",
        component: LogIn,
 //       redirect: "/dashboard",
    },
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
   
    ]
/*
const routes = [
    {
        path: "/",
        component: LogIn,
        children: [{
            path: "/dashboardLayout",
            name: "DashboardLayout",
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
        ]
    }
];
*/
export default routes;
