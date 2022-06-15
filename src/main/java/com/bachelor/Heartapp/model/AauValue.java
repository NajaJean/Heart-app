package com.bachelor.Heartapp.model;

public class AauValue {
	private String TimePulld;
	private String DateRec;
	private String TimeRec;
	private String DataTypeId;
	private String DataType;
	private String DataSrc;
	private String Value;
	private String Unit;

	public AauValue() {

	}

	public AauValue(String TimePulld, String DateRec, String TimeRec, String DataTypeId, String DataType,
			String DataSrc,
			String Value, String Unit) {
		super();
		this.TimePulld = TimePulld;
		this.DateRec = DateRec;
		this.TimeRec = TimeRec;
		this.DataTypeId = DataTypeId;
		this.DataType = DataType;
		this.DataSrc = DataSrc;
		this.Value = Value;
		this.Unit = Unit;
	}

	public String getTimePulld() {
		return TimePulld;
	}

	public void setTimePulld(String TimePulld) {
		this.TimePulld = TimePulld;
	}

	public String getDateRec() {
		return DateRec;
	}

	public void setDateRec(String DateRec) {
		this.DateRec = DateRec;
	}

	public String getTimeRec() {
		return TimeRec;
	}

	public void setTimeRec(String TimeRec) {
		this.TimeRec = TimeRec;
	}

	public String getDataTypeId() {
		return DataTypeId;
	}

	public void setDataTypeId(String DataTypeId) {
		this.DataTypeId = DataTypeId;
	}

	public String getDataType() {
		return DataType;
	}

	public void setDataType(String DataType) {
		this.DataType = DataType;
	}

	public String getDataSrc() {
		return DataSrc;
	}

	public void setDataSrc(String DataSrc) {
		this.DataSrc = DataSrc;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String Value) {
		this.Value = Value;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String Unit) {
		this.Unit = Unit;
	}

}