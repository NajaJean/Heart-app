package com.bachelor.Heartapp.controller;

import com.bachelor.Heartapp.model.Patient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
	
	private List<Patient> patients;
	
	public PatientController() {
		patients.add(new Patient());
		
	}
	
} 