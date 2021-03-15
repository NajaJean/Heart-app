package com.bachelor.Heartapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.DailyRecording;

public interface DailyRecordingRepository extends JpaRepository<DailyRecording, String>   {

	
}