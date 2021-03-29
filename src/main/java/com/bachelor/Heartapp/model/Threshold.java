package com.bachelor.Heartapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "thresholds")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Threshold {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name = "Patient_id")
	private String patientid;
	
	@Column(name = "Measurement_type")
	private String measurementtype;
	
	@Column(name = "Lower_threshold")
	private int lower_threshold;
	
	@Column(name = "Upper_threshold")
	private int upper_threshold;
	

	public Threshold(String patient_id, String measurement_type, int lower_threshold, int upper_threshold) {
		super();
		this.patientid = patient_id;
		this.measurementtype = measurement_type;
		this.lower_threshold = lower_threshold;
		this.upper_threshold = upper_threshold;
	}



	public String getPatientid() {
		return patientid;
	}



	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}



	public String getMeasurementtype() {
		return measurementtype;
	}



	public void setMeasurementtype(String measurementtype) {
		this.measurementtype = measurementtype;
	}



	public int getLower_threshold() {
		return lower_threshold;
	}

	public void setLower_threshold(int lower_threshold) {
		this.lower_threshold = lower_threshold;
	}

	public int getUpper_threshold() {
		return upper_threshold;
	}

	public void setUpper_threshold(int upper_threshold) {
		this.upper_threshold = upper_threshold;
	}

}
