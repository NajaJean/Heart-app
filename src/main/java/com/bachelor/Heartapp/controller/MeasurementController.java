package com.bachelor.Heartapp.controller;

import java.util.Date;
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

import com.bachelor.Heartapp.model.Measurement;
import com.bachelor.Heartapp.repository.MeasurementRepository;
import com.bachelor.Heartapp.BackendConfig;
	


//@CrossOrigin(origins = {"${app.api.settings.cross-origin.urls}"})
@RestController
@RequestMapping("/api")
public class MeasurementController {
	
	@Autowired
	MeasurementRepository measurementRepository;
	
	@GetMapping("/7measurements/{patient_id}")
	public ResponseEntity<List<Measurement>> get7LatestOfPatientsMeasurements(@PathVariable("patient_id") String patient_id) {
		try {
			List<Measurement> measurements = measurementRepository.findFirst7ByPatientidAndMeasurementtypeNotOrderByDatepostDesc(patient_id, "ECG");

			if (measurements.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(measurements, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/7measurements/{patient_id}/{measurement_type}")
	public ResponseEntity<List<Measurement>> get7LatestOfCertainMeasurement(@PathVariable("patient_id") String patient_id, @PathVariable("measurement_type") String measurement_type) {
		try {
			List<Measurement> measurements = measurementRepository.findFirst7ByPatientidAndMeasurementtypeOrderByDatepostDesc(patient_id,measurement_type);

			if (measurements.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(measurements, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/measurements/{patient_id}/{measurement_type}/{from}/{to}")
	public ResponseEntity<List<Measurement>> getMeasurementFromTo(@PathVariable("patient_id") String patient_id, @PathVariable("measurement_type") String measurement_type, @PathVariable("from") Date from, @PathVariable("to") Date to) {
		try {
			List<Measurement> measurements = measurementRepository.findAllByPatientidAndMeasurementtypeAndDatepostBetweenOrderByDatepostDesc(patient_id,measurement_type,from,to);

			if (measurements.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(measurements, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/measurements")
	public ResponseEntity<Measurement> createMeasurement(@RequestBody Measurement measurement) {
		try {
			Measurement _measurement = measurementRepository.save(new Measurement(
					measurement.getPatientid(),
					measurement.getDatepost(),
					measurement.getMeasurementtype(),
					measurement.getMeasurementvalue()
					));
			return new ResponseEntity<>(_measurement, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	



}
