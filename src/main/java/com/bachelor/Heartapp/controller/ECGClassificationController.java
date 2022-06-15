package com.bachelor.Heartapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/latestclassification/{patient_id}")
	public ResponseEntity<ECGClassification> getLatestECGClassification(@PathVariable("patient_id") String patient_id) {
		try {
					
			ECGClassification ecgClassification = ecgClassificationRepository.findFirstByPatientidOrderByTimestampDesc(patient_id);

			if (ecgClassification == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(ecgClassification, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
