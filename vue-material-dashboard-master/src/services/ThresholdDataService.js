import http from "../http-common";

class ThresholdDataService {
  getAll() {
    return http.get("/thresholds");
  }

  update(id, data) {
    return http.put(`/thresholds/${id}`, data);
  }
}

export default new ThresholdDataService();