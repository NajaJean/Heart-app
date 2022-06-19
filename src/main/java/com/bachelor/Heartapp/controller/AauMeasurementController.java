package com.bachelor.Heartapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bachelor.Heartapp.model.AauMeasurement;
import com.bachelor.Heartapp.model.AauValue;
import com.bachelor.Heartapp.model.Measurement;
import com.bachelor.Heartapp.repository.MeasurementRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.bachelor.Heartapp.HeartAppApplication;

@CrossOrigin(origins = HeartAppApplication.backendUrlRoot)
@RestController
@RequestMapping("/api")
public class AauMeasurementController {

	@Autowired
	MeasurementRepository measurementRepository;
	// `/aau/measurements/${patient_id}/${pwd}/${measurement_type}/${from}/${to}`

	@GetMapping("/aau/measurements/{subject_id}/{pwd}/{measurement_type}/{from}/{to}")
	public ResponseEntity<List<Measurement>> getMeasurementFromTo(
			@PathVariable("subject_id") String subject_id,
			@PathVariable("pwd") String pwd,
			@PathVariable("measurement_type") String measurement_type,
			@PathVariable("from") Date from,
			@PathVariable("to") Date to) {
		try {
			String mType = translateMeasurementType(measurement_type);

			long diff = (new Date()).getTime() - from.getTime();
			String noDays = Long.toString(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
			// Date fromMinus1 = new Date(from.getTime() - 86400000);
			// String fromMinus1String = new
			// SimpleDateFormat("yyyy-MM-dd").format(fromMinus1);

			Unirest.setTimeouts(0, 0);
			HttpResponse<String> response = Unirest.post("https://www.hjerteportalen.dk/api/v1/measured/data/")
					.header("Accept-Encoding", "gzip, deflate, br")
					.header("Connection", "keep-alive")
					.header("Authorization", "HjerteData 6f69ec8ace5fa3c948792bd6cad3a501")
					.field("SubjectId", subject_id)
					.field("Pwd", pwd)
					.field("Type", mType)
					.field("Days", noDays)
					.asString();

			String json = response.getBody();
			Gson gson = new GsonBuilder().create();
			AauMeasurement aauResult = gson.fromJson(json, AauMeasurement.class);

			String patientid = Integer.toString(aauResult.getSubjectId());

			List<Measurement> res = new ArrayList<Measurement>();

			for (int i = 0; i < aauResult.getNvals(); i++) {
				AauValue v = aauResult.getValues()[i];
				Date datepost = new SimpleDateFormat("yyyy-MM-dd").parse(v.getDateRec());
				Measurement m = new Measurement(patientid, datepost, measurement_type, v.getValue());

				if (datepost.before(to) || datepost.equals(to)) {
					if (datepost.after(from) || datepost.equals(from)) {
						res.add(m);
					}
				}
			}
			System.out.println(res);

			if (res.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	};

	@GetMapping("/aau/7measurements/{subject_id}/{pwd}/{measurement_type}")
	public ResponseEntity<List<Measurement>> get7LatestOfCertainMeasurement(
			@PathVariable("subject_id") String subject_id,
			@PathVariable("pwd") String pwd,
			@PathVariable("measurement_type") String measurement_type) {
		try {
			String mType = translateMeasurementType(measurement_type);

			Unirest.setTimeouts(0, 0);
			HttpResponse<String> response = Unirest.post("https://www.hjerteportalen.dk/api/v1/measured/data/")
					.header("Accept-Encoding", "gzip, deflate, br")
					.header("Connection", "keep-alive")
					.header("Authorization", "HjerteData 6f69ec8ace5fa3c948792bd6cad3a501")
					.field("SubjectId", subject_id)
					.field("Pwd", pwd)
					.field("Type", mType)
					.field("Days", "7")
					.asString();

			String json = response.getBody();
			Gson gson = new GsonBuilder().create();
			AauMeasurement aauResult = gson.fromJson(json, AauMeasurement.class);

			String patientid = Integer.toString(aauResult.getSubjectId());

			List<Measurement> res = new ArrayList<Measurement>();

			for (int i = 0; i < aauResult.getNvals(); i++) {
				AauValue v = aauResult.getValues()[i];
				Date datepost = new SimpleDateFormat("yyyy-MM-dd").parse(v.getDateRec());
				Measurement m = new Measurement(patientid, datepost, measurement_type, v.getValue());

				res.add(m);
			}
			System.out.println(res);

			if (res.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String translateMeasurementType(String measurementType) {
		int mtype;
		switch (measurementType) {
			case "weight":
				mtype = 1;
				break;
			case "blood_pressure_systolic":
				mtype = 2;
				break;
			case "blood_pressure_diastolic":
				mtype = 3;
				break;
			case "cnt_steps":
				mtype = 5;
				break;
			case "sleep_rem":
				mtype = 22;
				break;
			case "sleep_light":
				mtype = 23;
				break;
			case "sleep_deep":
				mtype = 24;
				break;
			default:
				mtype = 0;
		}

		return Integer.toString(mtype);
	}

}
