package com.bachelor.Heartapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "realtimedata")
public class RealTimeRecording {
	
	@Id
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
