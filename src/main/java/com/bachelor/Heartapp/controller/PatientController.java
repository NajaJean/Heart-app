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

import com.bachelor.Heartapp.BackendConfig;
import com.bachelor.Heartapp.model.Patient;
import com.bachelor.Heartapp.repository.PatientRepository;

@CrossOrigin(origins = BackendConfig.backendUrlRoot)
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
	
	@GetMapping("/patients/{patient_id}")
	public ResponseEntity<?> getPatient(@PathVariable("patient_id") String patient_id) {
		try {
			Patient patient = patientRepository.findByPatientid(patient_id);
			System.out.println(patient);
			if (patient == null) {
				return new ResponseEntity<>("There's no patient with this ID" , HttpStatus.NO_CONTENT);
			} 
			return new ResponseEntity<>(patient, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/patients")
	public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
		try {
			String id = patient.getPatient_id();
			String name = patient.getName();
			if (id.equals("") || name.equals("")) {
				return new ResponseEntity<>("Please fill out all information", HttpStatus.NO_CONTENT);
			}
			if (id.length() != 11) {
				return new ResponseEntity<>("Not a vaild ID", HttpStatus.BAD_REQUEST);
			}
			Patient _patient = patientRepository.save(new Patient(id, name));
			return new ResponseEntity<>(_patient, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Something went wrong, try again", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}