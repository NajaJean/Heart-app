package com.bachelor.Heartapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.ECGClassification;

public interface ECGClassificationRepository extends JpaRepository<ECGClassification, Long> {

	ECGClassification findFirstOrderByDatepostDesc();

}
