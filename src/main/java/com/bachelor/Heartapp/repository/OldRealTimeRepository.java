package com.bachelor.Heartapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.OldRealTime;

public interface OldRealTimeRepository extends JpaRepository<OldRealTime, String> {
	List<OldRealTime> findFirstByPatientidOrderByDatepostDesc(String patient_id);

	List<OldRealTime> findTop5000ByPatientidOrderByDatepostAsc(String patient_id);
}
