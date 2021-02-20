package com.bachelor.Heartapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "Daily_Recordings")
public class DailyRecording extends Measurement {
	
	@Column(name = "sleep_light")
	private int sleep_light;
	@Column(name = "sleep_deep")
	private int sleep_deep;
	@Column(name = "sleep_light")
	private int sleep_rem;
	@Column(name = "weight")
	private int weight;
	@Column(name = "cnt_steps")
	private int cnt_steps;
	@Column(name = "blood_pressure")
	private String blood_pressure;
	
	public DailyRecording(String patient_ID, int sleep_light, int sleep_deep, int sleep_rem, int weight, int cnt_steps,
			String blood_pressure) {
		super(patient_ID);
		this.sleep_light = sleep_light;
		this.sleep_deep = sleep_deep;
		this.sleep_rem = sleep_rem;
		this.weight = weight;
		this.cnt_steps = cnt_steps;
		this.blood_pressure = blood_pressure;
	}
		
	public int getSleep_light() {
		return sleep_light;
	}
	public int getSleep_deep() {
		return sleep_deep;
	}
	public int getSleep_rem() {
		return sleep_rem;
	}
	public int getWeight() {
		return weight;
	}
	public int getCnt_steps() {
		return cnt_steps;
	}
	public String getBlood_pressure() {
		return blood_pressure;
	}
	
	@Override
	public String toString() {
		return "Tutorial [Deep sleep=" + sleep_deep + "]";
	}

}
