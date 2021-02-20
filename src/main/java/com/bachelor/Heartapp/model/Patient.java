package com.bachelor.Heartapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient {

	@Id
	private String patient_ID;
	
	private String name;
	
	public Patient(String patient_ID, String name){
		this.patient_ID = patient_ID;
		this.name = name;
	}
	
	public String getPatient_ID() {
		return patient_ID;
	}
	public void setPatient_ID(String patient_ID) {
		this.patient_ID = patient_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*
	public int getAge() {
		int age = 0;
		this.patient_ID.charAt(0);
		
		return age;
	}*/
}
