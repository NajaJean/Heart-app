package com.bachelor.Heartapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "measurements")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Measurement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name = "Patient_id")
	private String patient_id;
	
	@Column(name = "Date")
	private String date_post;
	
	@Column(name = "Measurement_type")
	private String measurement_type;
	
	@Column(name = "Measurement_value")
	private String measurement_value;
	
	
	public Measurement() {

	}

	public Measurement(String patient_id, String date_post, String measurement_type,
			String measurement_value) {
		super();
		this.patient_id = patient_id;
		this.date_post = date_post;
		this.measurement_type = measurement_type;
		this.measurement_value = measurement_value;
	}


	public String getPatient_id() {
		return patient_id;
	}


	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}


	public String getDate_post() {
		return date_post;
	}


	public void setDate_post(String date_post) {
		this.date_post = date_post;
	}


	public String getMeasurement_type() {
		return measurement_type;
	}


	public void setMeasurement_type(String measurement_type) {
		this.measurement_type = measurement_type;
	}


	public String getMeasurement_value() {
		return measurement_value;
	}


	public void setMeasurement_value(String measurement_value) {
		this.measurement_value = measurement_value;
	}

	@Override
	public String toString() {
		return "Measurement [id=" + id + ", patient_id=" + patient_id + ", date_post=" + date_post
				+ ", measurement_type=" + measurement_type + ", measurement_value=" + measurement_value + "]";
	}
	
	


}
