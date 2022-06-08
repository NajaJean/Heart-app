package com.bachelor.Heartapp.repository;

import java.util.List;

//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
//import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.RealTime;

public interface RealTimeRepository extends JpaRepository<RealTime, String> {
	List<RealTime> findFirstByPatientidOrderByDatepostDesc(String patient_id);

	// Page<RealTime> findAllByPatientidOrderByDatepostAsc(Pageable pageable, String
	// patient_id);

	List<RealTime> findTop2000ByPatientidOrderByDatepostAsc(String patient_id);

	// List<RealTime> findByIdAndPatientidOrderByDatepostAsc(long id, String
	// patient_id);
}
