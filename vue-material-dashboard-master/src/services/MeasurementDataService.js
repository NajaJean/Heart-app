import http from "../http-common";

class MeasurementDataService {
  get7LatestMeasurements(patient_id) {
    return http.get(`/7measurements/${patient_id}`);
  }

  get7Latest(patient_id, measurement_type) {
    return http.get(`/7measurements/${patient_id}/${measurement_type}`);
  }

  getMeasurementsFromTo(patient_id, measurement_type, from, to) {
    return http.get(
      `/measurements/${patient_id}/${measurement_type}/${from}/${to}`
    );
  }

  getLatestECG(patient_id) {
    return http.get(`/latestecg/${patient_id}`);
  }

  getOldECG(index, patient_id) {
    return http.get(`/oldecg/${index}/${patient_id}`);
  }

}

export default new MeasurementDataService();
