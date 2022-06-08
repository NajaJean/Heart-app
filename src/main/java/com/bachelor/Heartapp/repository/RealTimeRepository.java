package com.bachelor.Heartapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.RealTime;

public interface RealTimeRepository extends JpaRepository<RealTime, String> {
	List<RealTime> findFirstByPatientidOrderByDatepostDesc(String patient_id);

	List<RealTime> findTop5000ByPatientidOrderByDatepostAsc(String patient_id);
}
