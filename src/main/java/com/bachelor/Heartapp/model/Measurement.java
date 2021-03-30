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
	
	@Column(name = "Patientid")
	private String patientid;
	
	@Column(name = "Date")
	private String datepost;
	
	@Column(name = "Measurementtype")
	private String measurementtype;
	
	@Column(name = "Measurementvalue")
	private String measurementvalue;
	
	
	public Measurement() {

	}

	public Measurement(String patientid, String datepost, String measurementtype,
			String measurementvalue) {
		super();
		this.patientid = patientid;
		this.datepost = datepost;
		this.measurementtype = measurementtype;
		this.measurementvalue = measurementvalue;
	}
	
	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}


	public String getDatepost() {
		return datepost;
	}


	public void setDatepost(String datepost) {
		this.datepost = datepost;
	}


	public String getMeasurementtype() {
		return measurementtype;
	}


	public void setMeasurementtype(String measurementtype) {
		this.measurementtype = measurementtype;
	}


	public String getMeasurementvalue() {
		return measurementvalue;
	}


	public void setMeasurementvalue(String measurementvalue) {
		this.measurementvalue = measurementvalue;
	}

	@Override
	public String toString() {
		return "Measurement [id=" + id + ", patientid=" + patientid + ", datepost=" + datepost
				+ ", measurementtype=" + measurementtype + ", measurementvalue=" + measurementvalue + "]";
	}
}
