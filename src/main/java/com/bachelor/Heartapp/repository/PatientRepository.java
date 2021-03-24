package com.bachelor.Heartapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, String>   {

}
