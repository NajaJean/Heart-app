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
	
	@Column(name = "Patientid")
	private String patientid;
	
	@Column(name = "Measurementtype")
	private String measurementtype;
	
	@Column(name = "Thresholdvalue")
	private int thresholdvalue;
	
	@Column(name = "Thresholdtype")
	private String thresholdtype;
	

	public Threshold(String patientid, String measurementtype, int thresholdvalue, String thresholdtype) {
		super();
		this.patientid = patientid;
		this.measurementtype = measurementtype;
		this.thresholdvalue = thresholdvalue;
		this.thresholdtype = thresholdtype;
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

	public int getThresholdvalue() {
		return thresholdvalue;
	}

	public void setThresholdvalue(int thresholdvalue) {
		this.thresholdvalue = thresholdvalue;
	}

	public String getThresholdtype() {
		return thresholdtype;
	}

	public void setThresholdtype(String thresholdtype) {
		this.thresholdtype = thresholdtype;
	}

}
