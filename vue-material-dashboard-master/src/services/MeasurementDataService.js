import http from "../http-common";

class MeasurementDataService {
  getAll() {
    return http.get("/measurements");
  }

  create(data) {
    return http.post("/measurements", data);
  }
}

export default new MeasurementDataService();