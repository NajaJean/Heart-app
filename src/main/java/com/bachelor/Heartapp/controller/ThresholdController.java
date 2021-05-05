package com.bachelor.Heartapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bachelor.Heartapp.HeartAppApplication;
import com.bachelor.Heartapp.model.Threshold;
import com.bachelor.Heartapp.repository.ThresholdRepository;

@CrossOrigin(origins = HeartAppApplication.backendUrlRoot)
@RestController
@RequestMapping("/api")
public class ThresholdController {

	@Autowired
	ThresholdRepository thresholdRepository;
	
	@GetMapping("/thresholds/{patientid}")
	public ResponseEntity<List<Threshold>> getPatientsThresholds(@PathVariable("patientid") String patientid) {
		try {
			List<Threshold> thresholds = thresholdRepository.findByPatientid(patientid);

			if (thresholds.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(thresholds, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/thresholds/{id}")
	public ResponseEntity<Threshold> updateThreshold(@PathVariable("id") long id, @RequestBody Threshold newThreshold) {
		if (id == 0) {
			try {
				Threshold _threshold = thresholdRepository.save(new Threshold(
						newThreshold.getPatientid(),
						newThreshold.getMeasurementtype(),
						newThreshold.getThresholdtype(),
						newThreshold.getThresholdvalue()
						));
				return new ResponseEntity<>(_threshold, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}			
		}
		else {
			return thresholdRepository.findById(id)
			.map(threshold -> {
				threshold.setThresholdvalue(newThreshold.getThresholdvalue());
				return new ResponseEntity<>(thresholdRepository.save(threshold), HttpStatus.OK);
			})
			.orElseGet(() -> {
				return new ResponseEntity<>(thresholdRepository.save(newThreshold), HttpStatus.CREATED);
			});
		}
	}
}
