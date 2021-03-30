package com.bachelor.Heartapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long>   {
	//Daily measurements:
	List<Measurement> findFirst7ByPatientidAndMeasurementtypeNotOrderByDatepostDesc(String patient_id, String measurement_type);
	List<Measurement> findFirst7ByPatientidAndMeasurementtypeOrderByDatepostDesc(String patient_id, String measurement_type);
	//ECG:
	List<Measurement> findFirst50ByPatientidAndMeasurementtypeOrderByDatepostDesc(String patient_id, String measurement_type);
	List<Measurement> findFirstByPatientidAndMeasurementtypeOrderByDatepostDesc(String patient_id, String measurement_type);
}
