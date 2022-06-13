package com.bachelor.Heartapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bachelor.Heartapp.model.ECGClassification;
import com.bachelor.Heartapp.repository.ECGClassificationRepository;
import com.bachelor.Heartapp.HeartAppApplication;

@CrossOrigin(origins = HeartAppApplication.backendUrlRoot)
@RestController
@RequestMapping("/api")
public class ECGClassificationController {

	@Autowired
	ECGClassificationRepository ecgClassificationRepository;

	// public ResponseEntity<?> getPatient(@PathVariable("patient_id") String
	// patient_id) {
	@GetMapping("/latestclassification")
	public ResponseEntity<?> getLatestECGClassification() {
		try {
			ECGClassification ecgClassification = ecgClassificationRepository.findFirstByOrderByTimestampDesc();

			if (ecgClassification == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(ecgClassification, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
