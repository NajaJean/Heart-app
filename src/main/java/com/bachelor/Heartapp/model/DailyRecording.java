package com.bachelor.Heartapp.model;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyRecording {

	private BigInteger blood_pressure_diastolic;
	private BigInteger blood_pressure_systolic;
	private BigInteger cnt_steps;	
	private String device_id;
	private String patient_id;
	private float csq;
	private String date_post;
	private BigInteger imei;
	private BigInteger sim_number;
	private float sleep_deep;
	private float sleep_light;
	private float sleep_rem;
	private float weight;
	
	public DailyRecording() {
		
	}
	
	public DailyRecording(BigInteger blood_pressure_diastolic, BigInteger blood_pressure_systolic, BigInteger cnt_steps,
			String device_id, String patient_id, float csq, String date_post, BigInteger imei, BigInteger sim_number, float sleep_deep,
			float sleep_light, float sleep_rem, float weight) {
		this.blood_pressure_diastolic = blood_pressure_diastolic;
		this.blood_pressure_systolic = blood_pressure_systolic;
		this.cnt_steps = cnt_steps;
		this.device_id = device_id;
		this.patient_id = patient_id;
		this.csq = csq;
		this.date_post = date_post;
		this.imei = imei;
		this.sim_number = sim_number;
		this.sleep_deep = sleep_deep;
		this.sleep_light = sleep_light;
		this.sleep_rem = sleep_rem;
		this.weight = weight;
	}

	public BigInteger getblood_pressure_systolic() {
		return blood_pressure_systolic;
	}
	public void setblood_pressure_systolic(BigInteger blood_pressure_systolic) {
		this.blood_pressure_systolic = blood_pressure_systolic;
	}
	public BigInteger getblood_pressure_diastolic() {
		return blood_pressure_diastolic;
	}
	public void setblood_pressure_diastolic(BigInteger blood_pressure_diastolic) {
		this.blood_pressure_diastolic = blood_pressure_diastolic;
	}
	public BigInteger getcnt_steps() {
		return cnt_steps;
	}
	public void setcnt_steps(BigInteger cnt_steps) {
		this.cnt_steps = cnt_steps;
	}
	public String getdevice_id() {
		return device_id;
	}
	public void setdevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getpatient_id() {
		return patient_id;
	}
	public void setpatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public float getCsq() {
		return csq;
	}
	public void setCsq(float csq) {
		this.csq = csq;
	}
	public String getDate_post() {
		return date_post;
	}
	public void setDate_post(String date_post) {
		this.date_post = date_post;
	}
	public BigInteger getImei() {
		return imei;
	}
	public void setImei(BigInteger imei) {
		this.imei = imei;
	}
	public BigInteger getSim_number() {
		return sim_number;
	}
	public void setSim_number(BigInteger sim_number) {
		this.sim_number = sim_number;
	}
	public float getSleep_deep() {
		return sleep_deep;
	}
	public void setSleep_deep(float sleep_deep) {
		this.sleep_deep = sleep_deep;
	}
	public float getSleep_light() {
		return sleep_light;
	}
	public void setSleep_light(float sleep_light) {
		this.sleep_light = sleep_light;
	}
	public float getSleep_rem() {
		return sleep_rem;
	}
	public void setSleep_rem(float sleep_rem) {
		this.sleep_rem = sleep_rem;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "DailyRecording [blood_pressure_systolic=" + blood_pressure_systolic
				+ ", Blood_Pressure_systolic=" + blood_pressure_diastolic + ", cnt_steps=" + cnt_steps + ", device_id="
				+ device_id + ", patient_id=" + patient_id + ", csq=" + csq + ", date_post=" + date_post + ", imei="
				+ imei + ", sim_number=" + sim_number + ", sleep_deep=" + sleep_deep + ", sleep_light=" + sleep_light
				+ ", sleep_rem=" + sleep_rem + ", weight=" + weight + "]";
	}

}
