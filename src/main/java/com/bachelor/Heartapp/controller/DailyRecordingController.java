package com.bachelor.Heartapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bachelor.Heartapp.model.DailyRecording;
import com.bachelor.Heartapp.repository.DailyRecordingRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class DailyRecordingController {
	
	@Autowired
	DailyRecordingRepository dailyRecordingRepository;

	@GetMapping("/dailyrecordings")
	public ResponseEntity<List<DailyRecording>> getAllDailyRecordings() {
		try {
			List<DailyRecording> dRecord = new ArrayList<DailyRecording>();
		
			dailyRecordingRepository.findAll().forEach(dRecord::add);

			if (dRecord.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(dRecord, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/dailyrecordings")
	public ResponseEntity<DailyRecording> createTutorial(@RequestBody DailyRecording dailyRecording) {
		try {
			DailyRecording _dRecord = dailyRecordingRepository.save(new DailyRecording(dailyRecording.getPatient_ID(), 
																					  dailyRecording.getSleep_light(), 
																					  dailyRecording.getSleep_deep(), 
																					  dailyRecording.getSleep_rem(), 
																					  dailyRecording.getWeight(), 
																					  dailyRecording.getCnt_steps(), 
																					  dailyRecording.getBlood_pressure()));
			return new ResponseEntity<>(_dRecord, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
