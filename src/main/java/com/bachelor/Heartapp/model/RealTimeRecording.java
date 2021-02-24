package com.bachelor.Heartapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "realtimedata")
public class RealTimeRecording {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "_patient_ID")
	private String patient_ID;
	
	//@CreationTimestamp
	//@Column(name = "date")
	//private LocalDateTime date;
	
	@Column(name = "_pulse")
	private int pulse;
	@Column(name = "_ECG")
	private int ECG;	
	
	public RealTimeRecording(String patient_ID, int pulse, int ecg) {
		this.patient_ID = patient_ID;
		this.pulse = pulse;
		ECG = ecg;
	}
	/*public LocalDateTime getDate() {
		return date;
	}*/
	public long getId() {
		return id;
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
