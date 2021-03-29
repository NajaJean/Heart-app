import http from "../http-common";

class ThresholdDataService {
  getPatientsThresholds(patient_id) {
    return http.get(`/thresholds/${patient_id}`);
  }

  updateThreshold(patient_id, measurement_type,threshold_type, data) {
    return http.put(`/thresholds/${patient_id}/${measurement_type}/${threshold_type}`, data);
  }

  createThreshold(patient_id, measurement_type,threshold_type, data) {
    return http.post(`/thresholds/${patient_id}/${measurement_type}/${threshold_type}`, data);
  }
}

export default new ThresholdDataService();