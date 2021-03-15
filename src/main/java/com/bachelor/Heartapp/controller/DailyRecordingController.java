package com.bachelor.Heartapp.controller;

import java.math.BigInteger;
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
			DailyRecording _dRecord = dailyRecordingRepository.save(new DailyRecording(dailyRecording.getBlood_pressure_diastolic(), 
																					  dailyRecording.getBlood_pressure_systolic(), 
																					  dailyRecording.getCnt_steps(), 
																					  dailyRecording.getDevice_id(), 
																					  dailyRecording.getPatient_id(), 
																					  dailyRecording.getCsq(), 
																					  dailyRecording.getDate_post(),
																					  dailyRecording.getImei(),
																					  dailyRecording.getSim_number(),
																					  dailyRecording.getSleep_deep(),
																					  dailyRecording.getSleep_light(),
																					  dailyRecording.getSleep_rem(),
																					  dailyRecording.getWeight()));
			return new ResponseEntity<>(_dRecord, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
/*	
	@GetMapping("/steps")
	public ResponseEntity<List<BigInteger>> getAllSteps() {
		try {
			List<BigInteger> steps = new ArrayList<BigInteger>();
			
			dailyRecordingRepository.findAll().forEach(d -> steps.add(d.getcnt_steps()));

			if (steps.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(steps, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	} */
}
