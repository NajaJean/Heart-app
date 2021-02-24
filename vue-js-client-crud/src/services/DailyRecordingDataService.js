import http from "../http-common";

class DailyRecordingDataService {
  getAll() {
    return http.get("/dailyrecordings");
  }

  create(data) {
    return http.post("/dailyrecordings", data);
  }
}

export default new DailyRecordingDataService();