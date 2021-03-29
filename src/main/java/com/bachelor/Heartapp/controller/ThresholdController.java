package com.bachelor.Heartapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bachelor.Heartapp.model.Threshold;
import com.bachelor.Heartapp.repository.ThresholdRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ThresholdController {

	@Autowired
	ThresholdRepository thresholdRepository;
	
	@PostMapping("/thresholds")
	public ResponseEntity<Threshold> createThreshold(@RequestBody Threshold threshold) {
		try {
			Threshold _threshold = thresholdRepository.save(new Threshold(
					threshold.getPatientid(),
					threshold.getMeasurementtype(),
					threshold.getLower_threshold(),
					threshold.getUpper_threshold()
					));
			return new ResponseEntity<>(_threshold, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/thresholds/{patient_id}")
	public ResponseEntity<List<Threshold>> getPatientsThresholds(@PathVariable("patient_id") String patient_id) {
		try {
			List<Threshold> thres = thresholdRepository.findByPatientid(patient_id);

			if (thres.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(thres, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/thresholds/{patient_id}/{measurement_type}")
	public ResponseEntity<Threshold> createThreshold(@PathVariable("patient_id") String patientid,@PathVariable("measurement_type") String measurementtype, @RequestBody Threshold threshold) {
		try {
			Threshold _threshold = thresholdRepository.save(new Threshold(patientid,measurementtype,threshold.getLower_threshold(), threshold.getUpper_threshold()));
			return new ResponseEntity<>(_threshold, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/thresholds/{patient_id}/{measurement_type}")
	public ResponseEntity<Threshold> updateThreshold(@PathVariable("patient_id") String patient_id,@PathVariable("measurement_type") String measurement_type, @RequestBody Threshold newThreshold) {
		return thresholdRepository.findByPatientidAndMeasurementtype(patient_id,measurement_type)
		.map(threshold -> {
			threshold.setLower_threshold(newThreshold.getLower_threshold());
			threshold.setUpper_threshold(newThreshold.getUpper_threshold());
			return new ResponseEntity<>(thresholdRepository.save(threshold), HttpStatus.OK);
		})
		.orElseGet(() -> {
			return new ResponseEntity<>(thresholdRepository.save(newThreshold), HttpStatus.CREATED);
		});
	}

	@GetMapping("/thresholds")
	public ResponseEntity<List<Threshold>> getAllThresholds() {
		try {
			List<Threshold> thresholds = new ArrayList<Threshold>();
		
			thresholdRepository.findAll().forEach(thresholds::add);

			if (thresholds.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(thresholds, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
