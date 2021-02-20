package com.bachelor.Heartapp.model;

import java.time.LocalDateTime;

import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

public class Measurement {
	
	@CreationTimestamp
	private LocalDateTime date;
	
	@Id
	private String patient_ID;	
	
	public Measurement() {

	}
	public Measurement(String patient_ID) {
		this.patient_ID = patient_ID;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getPatient_ID() {
		return patient_ID;
	}

}
