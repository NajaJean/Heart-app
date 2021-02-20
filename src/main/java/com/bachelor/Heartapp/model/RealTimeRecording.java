package com.bachelor.Heartapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "realTime_Data")
public class RealTimeRecording extends Measurement {
	
	@Column(name = "pulse")
	private int pulse;
	@Column(name = "ECG")
	private int ECG;	
	
	public RealTimeRecording(String patient_ID, int pulse, int ecg) {
		super(patient_ID);
		this.pulse = pulse;
		ECG = ecg;
	}
	
	public int getPulse() {
		return pulse;
	}
	public int getECG() {
		return ECG;
	}

}
