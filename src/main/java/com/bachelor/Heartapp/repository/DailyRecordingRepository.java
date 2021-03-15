package com.bachelor.Heartapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.DailyRecording;
import com.bachelor.Heartapp.model.Tutorial;

public interface DailyRecordingRepository extends JpaRepository<DailyRecording, Long>   {
	
}
