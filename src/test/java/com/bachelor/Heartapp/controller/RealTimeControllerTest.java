package com.bachelor.Heartapp.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

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
import com.bachelor.Heartapp.model.RealTime;
import com.bachelor.Heartapp.model.Threshold;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = HeartAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class RealTimeControllerTest {
	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	
	@Autowired
	RealTimeController realTimeController;
	
	HttpHeaders headers = new HttpHeaders();
	HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	
	Gson gson = new GsonBuilder().create();
	
	RealTime realTimeInDatabase;
	
	@BeforeEach
	public void init() throws JsonSyntaxException, JSONException {
		//Assert
		ResponseEntity<String> response = this.restTemplate.exchange(
				createURLWithPort("/latestecg/111"),
				HttpMethod.GET, entity, String.class);
		
		if (HttpStatus.NO_CONTENT == response.getStatusCode()) {
			RealTime r = new RealTime("111", new Date(),new Integer[]{1,2,3});
			realTimeController.createRealTime(r);
			
			ResponseEntity<String> realtimeAdded = restTemplate.exchange(
					createURLWithPort("/latestecg/111"),
					HttpMethod.GET, entity, String.class);
			System.out.println(realtimeAdded);
			realTimeInDatabase = gson.fromJson((new JSONArray(realtimeAdded.getBody())).getJSONObject(0).toString(), RealTime.class);	
		} else {
			realTimeInDatabase = gson.fromJson((new JSONArray(response.getBody())).getJSONObject(0).toString(), RealTime.class);
		}
    }
	
	@Test
	public void testGetRealTime() throws JSONException {
		//Act
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/latestecg/111"),
				HttpMethod.GET, entity, String.class);
		
		//Assert
		RealTime gotRealTime = gson.fromJson((new JSONArray(response.getBody())).getJSONObject(0).toString(), RealTime.class);
		
		assertEquals(realTimeInDatabase.getPatientid(), gotRealTime.getPatientid());
		assertEquals(realTimeInDatabase.getDatepost(), gotRealTime.getDatepost());
		assertEquals(realTimeInDatabase.getMeasurementvalue()[0], gotRealTime.getMeasurementvalue()[0]);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGetRealTimeNoContent() throws JSONException {	
		//Act
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/latestecg/999"),
				HttpMethod.GET, entity, String.class);
		
		//Assert
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	/*@Test
    public void testPostRealTime() throws JSONException {
		
		
        //Act 
		RealTime r = new RealTime("112", new Date(),new Integer[]{1,2,3});

		
		HttpEntity<RealTime> realtimeToPost = new HttpEntity<RealTime>(r, headers);
		restTemplate.exchange(
				createURLWithPort("/latestecg/112"),
				HttpMethod.POST, realtimeToPost, String.class);
        
        //Assert
        ResponseEntity<String> responseAfterPost = restTemplate.exchange(
				createURLWithPort("/latestecg/112"),
				HttpMethod.GET, entity, String.class);
		RealTime postedRealTime = gson.fromJson((new JSONArray(responseAfterPost.getBody())).getJSONObject(0).toString(), RealTime.class);
		
		assertEquals(1, postedRealTime.getMeasurementvalue()[0]);
    }*/
	
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port +"/api"+ uri;
	}

}
