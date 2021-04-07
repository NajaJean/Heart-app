package com.bachelor.Heartapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bachelor.Heartapp.model.RealTime;
import com.bachelor.Heartapp.repository.RealTimeRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RealTimeController {

	@Autowired
	RealTimeRepository realtimeRepository;
	
	@PostMapping("/realtime")
	public ResponseEntity<RealTime> createRealTime(@RequestBody RealTime realtime) {
		try {
			RealTime _realtime = realtimeRepository.save(new RealTime(
					realtime.getPatientid(),
					realtime.getDatepost(),
					realtime.getMeasurementtype(),
					realtime.getMeasurementvalue()
					));
			return new ResponseEntity<>(_realtime, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/125ecg/{patient_id}")
	public ResponseEntity<List<RealTime>> get125LatestOfPatientsECG(@PathVariable("patient_id") String patient_id) {
		try {
			List<RealTime> ecgs = realtimeRepository.findFirst125ByPatientidAndMeasurementtypeOrderByDatepostDesc(patient_id,"ECG");

			if (ecgs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(ecgs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/latestecg/{patient_id}")
	public ResponseEntity<List<RealTime>> getLatestPatientsECG(@PathVariable("patient_id") String patient_id) {
		try {
			List<RealTime> ecgs = realtimeRepository.findFirstByPatientidAndMeasurementtypeOrderByDatepostDesc(patient_id,"ECG");

			if (ecgs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(ecgs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}