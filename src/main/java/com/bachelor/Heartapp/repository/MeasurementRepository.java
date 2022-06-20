package com.bachelor.Heartapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
	List<Measurement> findFirst7ByPatientidAndMeasurementtypeOrderByDatepostDesc(String patient_id,
			String measurement_type);

	List<Measurement> findAllByPatientidAndMeasurementtypeAndDatepostBetweenOrderByDatepostDesc(String patient_id,
			String measurement_type, Date from, Date to);

	List<Measurement> findFirst7ByPatientidAndMeasurementtypeNotOrderByDatepostDesc(String patient_id,
			String measurement_type);

	List<Measurement> findAllByPatientidOrderByDatepostDesc(String patient_id);

	List<Measurement> findAllByPatientidAndMeasurementtypeOrderByDatepostDesc(String patient_id,
			String measurement_type);
}
