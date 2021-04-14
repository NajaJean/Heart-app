package com.bachelor.Heartapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bachelor.Heartapp.HeartAppApplication;

@Entity
@Table(name = HeartAppApplication.realtimeTableName)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RealTime {
	
	@Column(name = "patientid")
	private String patientid;
	
	@Id
	@Column(name = "timestamp")
	private Date datepost;
	
	
	@Column(name = "ecgdata")
	private int[] measurementvalue;
	
	public RealTime() {
		
	}

	public RealTime(String patientid, Date datepost, int[] measurementvalue) {
		super();
		this.patientid = patientid;
		this.datepost = datepost;
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

	public int[] getMeasurementvalue() {
		return measurementvalue;
	}

	public void setMeasurementvalue(int[] measurementvalue) {
		this.measurementvalue = measurementvalue;
	}
	

}
