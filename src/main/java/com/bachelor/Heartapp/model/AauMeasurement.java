package com.bachelor.Heartapp.model;

public class AauMeasurement {
	private int SubjectId;
	private String TimeStamp;
	private String DateStart;
	private String DateEnd;
	private int Nvals;
	private AauValue[] Values;

	public AauMeasurement() {

	}

	public AauMeasurement(int SubjectId, String TimeStamp, String DateStart, String DateEnd, int Nvals,
			AauValue[] Values) {
		super();
		this.SubjectId = SubjectId;
		this.TimeStamp = TimeStamp;
		this.DateStart = DateStart;
		this.DateEnd = DateEnd;
		this.Nvals = Nvals;
		this.Values = Values;
	}

	public int getSubjectId() {
		return SubjectId;
	}

	public void setSubjectId(int SubjectId) {
		this.SubjectId = SubjectId;
	}

	public String getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(String TimeStamp) {
		this.TimeStamp = TimeStamp;
	}

	public String getDateStart() {
		return DateStart;
	}

	public void setDateStart(String DateStart) {
		this.DateStart = DateStart;
	}

	public String getDateEnd() {
		return DateEnd;
	}

	public void setDateEnd(String DateEnd) {
		this.DateEnd = DateEnd;
	}

	public int getNvals() {
		return Nvals;
	}

	public void setNvals(int Nvals) {
		this.Nvals = Nvals;
	}

	public AauValue[] getValues() {
		return Values;
	}

	public void setValues(AauValue[] Values) {
		this.Values = Values;
	}
}
