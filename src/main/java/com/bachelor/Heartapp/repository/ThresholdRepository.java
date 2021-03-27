package com.bachelor.Heartapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.Threshold;

public interface ThresholdRepository extends JpaRepository<Threshold, Long> {
    List<Threshold> findByPatientid(String patient_id);
    Optional<Threshold> findByPatientidAndMeasurementtype(String patient_id, String measurement_type);
}
