package com.bachelor.Heartapp.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bachelor.Heartapp.model.RealTimeRecording;

public interface RealTimeRecordingRepository extends JpaRepository<RealTimeRecording, String>   {

}
