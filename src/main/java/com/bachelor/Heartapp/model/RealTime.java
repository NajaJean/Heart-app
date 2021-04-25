package com.bachelor.Heartapp.model;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bachelor.Heartapp.HeartAppApplication;

@Entity
@Table(name = HeartAppApplication.realtimeTableName)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RealTime {
	
	@Id
	private long id;
	
	@Column(name = "patient_id")
	private String patientid;
	
	@Column(name = "timestamp")
	private Date datepost;
	
    @Type(type = "com.bachelor.Heartapp.model.helpertypes.IntArrayUserType")
	@Column(name = "ecg_data")
	private Integer[] measurementvalue;
	
	public RealTime() {
		
	}

	public RealTime(String patientid, Date datepost, Integer[] measurementvalue) {
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

	public Integer[] getMeasurementvalue() {
		return measurementvalue;
	}

	public void setMeasurementvalue(Integer[] measurementvalue) {
		this.measurementvalue = measurementvalue;
	}

	@Override
	public String toString() {
		return "RealTime [id=" + id + ", patientid=" + patientid + ", datepost=" + datepost + ", measurementvalue="
				+ Arrays.toString(measurementvalue) + "]";
	}


}
