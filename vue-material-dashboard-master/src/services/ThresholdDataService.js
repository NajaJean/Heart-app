import http from "../http-common";

class ThresholdDataService {
  getPatientsThresholds(patient_id) {
    return http.get(`/thresholds/${patient_id}`);
  }

  updateThreshold(id, data) {
    return http.put(`/thresholds/${id}`, data);
  }

  deleteThreshold(id) {
    return http.delete(`/thresholds/${id}`)
  }
}

export default new ThresholdDataService();
