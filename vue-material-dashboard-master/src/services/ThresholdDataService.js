import http from "../http-common";

class ThresholdDataService {
  getPatientsThresholds(patient_id) {
    return http.get(`/thresholds/${patient_id}`);
  }

  updateThreshold(id, data) {
    return http.put(`/thresholds/${id}`, data);
  }
}

export default new ThresholdDataService();