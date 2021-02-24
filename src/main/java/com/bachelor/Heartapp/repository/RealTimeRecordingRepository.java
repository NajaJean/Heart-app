package com.bachelor.Heartapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bachelor.Heartapp.model.RealTimeRecording;

public interface RealTimeRecordingRepository extends JpaRepository<RealTimeRecording, Long>   {

}
