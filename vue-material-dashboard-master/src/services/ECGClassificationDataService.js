import http from "../http-common";

class ECGClassificationDataService {
  getLatestECGClassification() {
    return http.get(`/latestclassification`);
  }
}

export default new ECGClassificationDataService();
