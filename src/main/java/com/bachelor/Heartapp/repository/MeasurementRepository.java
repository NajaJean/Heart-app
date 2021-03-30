package com.bachelor.Heartapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long>   {
	List<Measurement> findTop7ByPatientidAndMeasurementtypeNot(String patient_id, String measurement_type);
	List<Measurement> findFirst50ByPatientidAndMeasurementtypeOrderByDatepostDesc(String patient_id, String measurement_type);
	List<Measurement> findFirstByPatientidAndMeasurementtypeOrderByDatepostDesc(String patient_id, String measurement_type);
	List<Measurement> findTop7ByPatientidAndMeasurementtype(String patient_id, String measurement_type);

}
