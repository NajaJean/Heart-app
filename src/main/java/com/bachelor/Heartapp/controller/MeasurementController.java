package com.bachelor.Heartapp.controller;

import java.util.ArrayList;
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


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MeasurementController {
		
	@Autowired
	MeasurementRepository measurementRepository;

	@GetMapping("/measurements")
	public ResponseEntity<List<Measurement>> getAllMeasurements() {
		try {
			List<Measurement> measurements = new ArrayList<Measurement>();
		
			measurementRepository.findAll().forEach(measurements::add);

			if (measurements.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(measurements, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/7measurements/{patient_id}")
	public ResponseEntity<List<Measurement>> get7LatestOfPatientsMeasurements(@PathVariable("patient_id") String patient_id) {
		try {
			List<Measurement> measurements = measurementRepository.findTop7ByPatientid(patient_id);

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
			List<Measurement> measurements = measurementRepository.findTop7ByPatientidAndMeasurementtype(patient_id,measurement_type);

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
					measurement.getPatient_id(),
					measurement.getDate_post(),
					measurement.getMeasurement_type(),
					measurement.getMeasurement_value()
					));
			return new ResponseEntity<>(_measurement, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
