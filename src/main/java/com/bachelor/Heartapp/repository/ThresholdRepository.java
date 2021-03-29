package com.bachelor.Heartapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.Threshold;

public interface ThresholdRepository extends JpaRepository<Threshold, Long> {
    List<Threshold> findByPatientid(String patientid);
    Optional<Threshold> findByPatientidAndMeasurementtypeAndThresholdtype(String patientid, String measurementtype, String thresholdtype);
}
