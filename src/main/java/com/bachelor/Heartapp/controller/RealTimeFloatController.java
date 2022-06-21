package com.bachelor.Heartapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bachelor.Heartapp.HeartAppApplication;
import com.bachelor.Heartapp.model.RealTimeFloat;
import com.bachelor.Heartapp.repository.RealTimeFloatRepository;

@CrossOrigin(origins = HeartAppApplication.backendUrlRoot)
@RestController
@RequestMapping("/api")
public class RealTimeFloatController {

	@Autowired
	RealTimeFloatRepository realtimeRepository;

	@PostMapping("/realtimefloat")
	public ResponseEntity<RealTimeFloat> createRealTime(@RequestBody RealTimeFloat realtime) {
		try {
			RealTimeFloat r = new RealTimeFloat(
					realtime.getPatientid(),
					realtime.getDatepost(),
					realtime.getMeasurementvalue());

			if (r.getDatepost().equals(null) || r.getMeasurementvalue().length == 0 || r.getPatientid().equals("")) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			} else {
				realtimeRepository.save(r);
				return new ResponseEntity<>(r, HttpStatus.CREATED);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/latestecgfloat/{patient_id}")
	public ResponseEntity<List<RealTimeFloat>> getLatestPatientsECG(@PathVariable("patient_id") String patient_id) {
		try {
			List<RealTimeFloat> ecgs = realtimeRepository.findFirstByPatientidOrderByDatepostDesc(patient_id);

			if (ecgs.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(ecgs, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
