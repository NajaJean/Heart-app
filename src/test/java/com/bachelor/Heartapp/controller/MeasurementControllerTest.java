package com.bachelor.Heartapp.controller;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.bachelor.Heartapp.model.Measurement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HeartAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class MeasurementControllerTest {
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	
	@Autowired
	MeasurementController measurementController;
	
	Measurement measurementInDatabase;
	
	Gson gson = new GsonBuilder().create();
	
	@BeforeEach
	public void init() throws JsonSyntaxException, JSONException {
		//Assert
		ResponseEntity<String> response = this.restTemplate.exchange(
				createURLWithPort("/7measurements/111/cnt_steps"),
				HttpMethod.GET, entity, String.class);
		
		if (HttpStatus.NO_CONTENT == response.getStatusCode()) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
			Date d = null;
			try {
				d = dateFormat.parse("2021-04-12T06:00:00.000+0000");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			Measurement m = new Measurement("111",d,"cnt_steps", "4000");
			measurementController.createMeasurement(m);
			
			ResponseEntity<String> measurementAdded = restTemplate.exchange(
					createURLWithPort("/7measurements/111"),
					HttpMethod.GET, entity, String.class);
			measurementInDatabase = gson.fromJson((new JSONArray(measurementAdded.getBody())).getJSONObject(0).toString(), Measurement.class);	
		} else {
			measurementInDatabase = gson.fromJson((new JSONArray(response.getBody())).getJSONObject(0).toString(), Measurement.class);
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
	public void testGet7MeasurementsOfCertainType() throws JSONException {
		//Act
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/7measurements/111/cnt_steps"),
				HttpMethod.GET, entity, String.class);
		
		//Assert
		Measurement gotMeasurement = gson.fromJson((new JSONArray(response.getBody())).getJSONObject(0).toString(), Measurement.class);
		
		assertEquals(measurementInDatabase.getMeasurementtype(), gotMeasurement.getMeasurementtype());
		assertEquals(measurementInDatabase.getPatientid(), gotMeasurement.getPatientid());
		assertEquals(measurementInDatabase.getMeasurementvalue(), gotMeasurement.getMeasurementvalue());
		assertEquals(measurementInDatabase.getDatepost(), gotMeasurement.getDatepost());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGet7MeasurementsOfNotExistingType() throws JSONException {
		//Act
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/7measurements/111/not_a_type_in_DB"),
				HttpMethod.GET, entity, String.class);
		
		//Assert
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testGetMeasurementsFromTo() throws JSONException {
		//Act
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/measurements/111/cnt_steps/Sat Apr 10 2021 00:00:00 GMT+0100/Wed Apr 14 2021 00:00:00 GMT+0100"),
				HttpMethod.GET, entity, String.class);
		
		//Assert
		Measurement gotMeasurement = gson.fromJson((new JSONArray(response.getBody())).getJSONObject(0).toString(), Measurement.class);
		assertEquals(measurementInDatabase.getMeasurementtype(), gotMeasurement.getMeasurementtype());
		assertEquals(measurementInDatabase.getPatientid(), gotMeasurement.getPatientid());
		assertEquals(measurementInDatabase.getMeasurementvalue(), gotMeasurement.getMeasurementvalue());
		assertEquals(measurementInDatabase.getDatepost(), gotMeasurement.getDatepost());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGetMeasurementsFromToNoContent() throws JSONException {
		//Act
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/measurements/111/cnt_steps/Sat Apr 10 2019 00:00:00 GMT+0100/Wed Apr 14 2019 00:00:00 GMT+0100"),
				HttpMethod.GET, entity, String.class);
		
		//Assert
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
    public void testPostMeasurement() {
		Measurement measurement = new Measurement("111", new Date(),"type","1");
        ResponseEntity<String> responseEntity = this.restTemplate
            .postForEntity(createURLWithPort("/measurements"), measurement, String.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
	
	@Test
    public void testPostMeasurementBadRequest() {
		Measurement measurement = new Measurement("", new Date(),"","");
        ResponseEntity<String> responseEntity = this.restTemplate
            .postForEntity(createURLWithPort("/measurements"), measurement, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port +"/api"+ uri;
	}
}
