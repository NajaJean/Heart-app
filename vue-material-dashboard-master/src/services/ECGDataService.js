import http from "../http-common";

class ECGDataService {
  getLatestTenSec() {
    return http.get("/ecg10");
  }

  getLatest() {
    return http.get("/ecg1");
  }
}

export default new ECGDataService();