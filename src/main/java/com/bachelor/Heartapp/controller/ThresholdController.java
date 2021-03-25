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
	
	@PutMapping("/thresholds/{id}")
	public ResponseEntity<Threshold> createThreshold(@PathVariable("id") long id,@RequestBody Threshold threshold) {
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
}
