package com.bachelor.Heartapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bachelor.Heartapp.model.Patient;
import com.bachelor.Heartapp.repository.PatientRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PatientController {
		
	@Autowired
	PatientRepository patientRepository;

	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> getAllPatients() {
		try {
			List<Patient> patients = new ArrayList<Patient>();
		
			patientRepository.findAll().forEach(patients::add);

			if (patients.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(patients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/patients/{id}")
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
		try {
			Patient _patient = patientRepository.save(new Patient(
                patient.getPatient_id(),
                patient.getName()
				));
			return new ResponseEntity<>(_patient, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}