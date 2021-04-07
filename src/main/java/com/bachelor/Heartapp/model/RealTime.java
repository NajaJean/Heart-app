package com.bachelor.Heartapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "realtime")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RealTime {
	
	@Column(name = "Patientid")
	private String patientid;
	
	@Id
	@Column(name = "Date")
	private Date datepost;
	
	@Column(name = "Measurementtype")
	private String measurementtype;
	
	@Column(name = "Measurementvalue")
	private int[] measurementvalue;
	
	public RealTime() {
		
	}

	public RealTime(String patientid, Date datepost, String measurementtype, int[] measurementvalue) {
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

	public Date getDatepost() {
		return datepost;
	}

	public void setDatepost(Date datepost) {
		this.datepost = datepost;
	}

	public String getMeasurementtype() {
		return measurementtype;
	}

	public void setMeasurementtype(String measurementtype) {
		this.measurementtype = measurementtype;
	}

	public int[] getMeasurementvalue() {
		return measurementvalue;
	}

	public void setMeasurementvalue(int[] measurementvalue) {
		this.measurementvalue = measurementvalue;
	}
	

}
