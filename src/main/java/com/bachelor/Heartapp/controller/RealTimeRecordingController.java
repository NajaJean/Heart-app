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

import com.bachelor.Heartapp.model.RealTimeRecording;
import com.bachelor.Heartapp.repository.RealTimeRecordingRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RealTimeRecordingController {
	
	@Autowired
	RealTimeRecordingRepository realTimeRecordingRepository;
	
	@GetMapping("/realtimerecordings")
	  public ResponseEntity<List<RealTimeRecording>> getAllRealTimeRecordings() {
			try {
				List<RealTimeRecording> realTimeRecordings = new ArrayList<RealTimeRecording>();

				realTimeRecordingRepository.findAll().forEach(realTimeRecordings::add);
				
				if (realTimeRecordings.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}

				return new ResponseEntity<>(realTimeRecordings, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
	
	@PostMapping("/realtimerecordings")
	  public ResponseEntity<RealTimeRecording> createRealTimeRecording(@RequestBody RealTimeRecording realtimerecording) {
			try {
				RealTimeRecording realTimeRec = realTimeRecordingRepository.save(new RealTimeRecording(realtimerecording.getPatient_ID(), realtimerecording.getPulse(), realtimerecording.getECG()));
				return new ResponseEntity<>(realTimeRec, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	  }
}
