package com.bachelor.Heartapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "patients")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient {
	
	@Id
	@Column(name = "Patient_id")
	private String patient_id;
	
	@Column(name = "Name")
	private String name;

	public Patient() {
		
	}
	
	public Patient(String patient_id, String name) {
		this.patient_id = patient_id;
		this.name = name;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
