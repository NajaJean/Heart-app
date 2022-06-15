import http from "../http-common";

class ECGClassificationDataService {
  getLatestECGClassification(patient_id) {
    return http.get(`/latestclassification/${patient_id}`);
  }
}

export default new ECGClassificationDataService();
