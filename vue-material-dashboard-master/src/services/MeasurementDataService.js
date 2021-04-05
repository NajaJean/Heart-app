import http from "../http-common";

class MeasurementDataService {

  get7LatestMeasurements(patient_id) {
    return http.get(`/7measurements/${patient_id}`);
  }

  get7Latest(patient_id,measurement_type) {
    return http.get(`/7measurements/${patient_id}/${measurement_type}`);
  }

  get125LatestECG(patient_id) {
    return http.get(`/125ecg/${patient_id}`);
  }

  getLatestECG(patient_id) {
    return http.get(`/latestecg/${patient_id}`);
  }

}

export default new MeasurementDataService();