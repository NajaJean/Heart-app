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
					threshold.getPatient_id(),
					threshold.getMeasurement_type(),
					threshold.getLower_threshold(),
					threshold.getUpper_threshold()
					));
			return new ResponseEntity<>(_threshold, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/thresholds/{patient_id}")
	public ResponseEntity<List<Threshold>> getThresholdsForPatient(@PathVariable("patient_id") String patient_id) {
		try {
			List<Threshold> thresholds = thresholdRepository.findByPatientid(patient_id);

			if (thresholds.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(thresholds, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/thresholds/{patient_id}/{measurement_type}")
	public ResponseEntity<Threshold> updateThreshold(@PathVariable("patient_id") String patient_id,@PathVariable("measurement_type") String measurement_type, @RequestBody Threshold threshold) {
		Optional<Threshold> thresholdData = thresholdRepository.findByPatientidAndMeasurementtype(patient_id,measurement_type);

		if (thresholdData.isPresent()) {
			Threshold _threshold = thresholdData.get();
			if (threshold.getLower_threshold() != null) {
				_threshold.setLower_threshold(threshold.getLower_threshold());
			}
			if (threshold.getUpper_threshold() != null) {
				_threshold.setUpper_threshold(threshold.getUpper_threshold());
			}
			return new ResponseEntity<>(thresholdRepository.save(_threshold), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
