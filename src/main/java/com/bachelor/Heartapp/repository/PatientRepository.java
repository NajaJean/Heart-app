package com.bachelor.Heartapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bachelor.Heartapp.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
	
}