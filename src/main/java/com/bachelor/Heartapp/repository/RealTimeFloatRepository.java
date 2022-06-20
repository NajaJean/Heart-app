package com.bachelor.Heartapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.RealTimeFloat;

public interface RealTimeFloatRepository extends JpaRepository<RealTimeFloat, String> {
	List<RealTimeFloat> findFirstByPatientidOrderByDatepostDesc(String patient_id);

	List<RealTimeFloat> findTop5000ByPatientidOrderByDatepostAsc(String patient_id);
}
