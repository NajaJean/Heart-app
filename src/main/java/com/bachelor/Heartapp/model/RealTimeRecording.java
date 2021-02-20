package com.bachelor.Heartapp.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(schema = "realTime_Data")
public class RealTimeRecording {
	
	@Id
	@Column(name = "patient_ID")
	private String patient_ID;
	
	@CreationTimestamp
	@Column(name = "date")
	private LocalDateTime date;
	
	@Column(name = "pulse")
	private int pulse;
	@Column(name = "ECG")
	private int ECG;	
	
	public RealTimeRecording(String patient_ID, int pulse, int ecg) {
		this.patient_ID = patient_ID;
		this.pulse = pulse;
		ECG = ecg;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public String getPatient_ID() {
		return patient_ID;
	}
	public int getPulse() {
		return pulse;
	}
	public int getECG() {
		return ECG;
	}

}
