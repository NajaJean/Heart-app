package com.bachelor.Heartapp.controller;

import static org.junit.Assert.assertEquals;


import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bachelor.Heartapp.HeartAppApplication;
import com.bachelor.Heartapp.model.Threshold;
import com.google.gson.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HeartAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ThresholdControllerTest {
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	
	@Autowired
	ThresholdController thresholdController;

	HttpHeaders headers = new HttpHeaders();
	HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	
	Gson gson = new GsonBuilder().create();
	
	Threshold thresholdInDatabase;
	
	@BeforeEach
	public void init() throws JsonSyntaxException, JSONException {
		//Assert
		ResponseEntity<String> response = this.restTemplate.exchange(
				createURLWithPort("/thresholds/111"),
				HttpMethod.GET, entity, String.class);
		
		if (HttpStatus.NO_CONTENT == response.getStatusCode()) {
			Threshold t = new Threshold("111","cnt_steps","lower",3000);
			thresholdController.updateThreshold(0,t);
			
			ResponseEntity<String> thresAdded = restTemplate.exchange(
					createURLWithPort("/thresholds/111"),
					HttpMethod.GET, entity, String.class);
			thresholdInDatabase = gson.fromJson((new JSONArray(thresAdded.getBody())).getJSONObject(0).toString(), Threshold.class);	
		} else {
			thresholdInDatabase = gson.fromJson((new JSONArray(response.getBody())).getJSONObject(0).toString(), Threshold.class);
		}
    }
	
	@Test
	public void badURL() throws JSONException {
		//Act
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/notAnUrl"),
				HttpMethod.GET, entity, String.class);
		System.out.println(response.getStatusCode());
		//Assert
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void testGetThresholds() throws JSONException {
		//Act
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/thresholds/111"),
				HttpMethod.GET, entity, String.class);
		
		//Assert
		Threshold gotThreshold = gson.fromJson((new JSONArray(response.getBody())).getJSONObject(0).toString(), Threshold.class);
		
		assertEquals(thresholdInDatabase.getId(), gotThreshold.getId());
		assertEquals(thresholdInDatabase.getMeasurementtype(), gotThreshold.getMeasurementtype());
		assertEquals(thresholdInDatabase.getThresholdtype(), gotThreshold.getThresholdtype());
		assertEquals(thresholdInDatabase.getPatientid(), gotThreshold.getPatientid());
		assertEquals(thresholdInDatabase.getThresholdvalue(), gotThreshold.getThresholdvalue());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGetThresholdsNoContent() throws JSONException {	
		//Act
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/thresholds/999"),
				HttpMethod.GET, entity, String.class);
		
		//Assert
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
    public void testPutThresholdBadRequest() {
		//Arrange
		Threshold t = new Threshold("1","","",0);
		HttpEntity<Threshold> entityPut = new HttpEntity<>(t);
		
		//Act
		ResponseEntity<Threshold> response = restTemplate.exchange(
				createURLWithPort("/thresholds/0"),
				HttpMethod.PUT, entityPut, Threshold.class);
		
		//Assert
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
	
	
	@Test
    public void testPutThreshold() throws JSONException {
        //Act
   	 	thresholdInDatabase.setThresholdvalue(6000);
        restTemplate.put(createURLWithPort("/thresholds/"+thresholdInDatabase.getId()), thresholdInDatabase);
        
        //Assert
        ResponseEntity<String> responseAfterUpdate = restTemplate.exchange(
				createURLWithPort("/thresholds/111"),
				HttpMethod.GET, entity, String.class);
		Threshold updatedThreshold = gson.fromJson((new JSONArray(responseAfterUpdate.getBody())).getJSONObject(0).toString(), Threshold.class);
		
		assertEquals(6000, updatedThreshold.getThresholdvalue());
    }

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port +"/api"+ uri;
	}
	
}