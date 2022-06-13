package com.bachelor.Heartapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.bachelor.Heartapp.HeartAppApplication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = HeartAppApplication.ecgClassificationTableName)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ECGClassification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "patient_id")
	private String patientid;

	@Column(name = "ml_data")
	private int mldata;

	@Column(name = "datepost")
	private Date datepost;

	public ECGClassification() {

	}

	public ECGClassification(String patientid, int mldata, Date datepost) {
		super();
		this.patientid = patientid;
		this.datepost = datepost;
		this.mldata = mldata;
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

	public int getMldata() {
		return mldata;
	}

	public void setMldata(int mldata) {
		this.mldata = mldata;
	}

	@Override
	public String toString() {
		return "Measurement [id=" + id + ", patientid=" + patientid + ", datepost=" + datepost
				+ ", mldata=" + mldata + "]";
	}
}
