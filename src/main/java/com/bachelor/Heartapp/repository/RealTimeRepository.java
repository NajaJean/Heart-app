package com.bachelor.Heartapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.RealTime;

public interface RealTimeRepository extends JpaRepository<RealTime, String> {
	List<RealTime> findFirst50ByPatientidAndMeasurementtypeOrderByDatepostDesc(String patient_id, String measurement_type);
	List<RealTime> findFirstByPatientidAndMeasurementtypeOrderByDatepostDesc(String patient_id, String measurement_type);
}
