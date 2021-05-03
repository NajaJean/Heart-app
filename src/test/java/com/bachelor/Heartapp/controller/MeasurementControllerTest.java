package com.bachelor.Heartapp.controller;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
				createURLWithPort("/7measurements/111"),
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
	public void testGet7Measurements() throws JSONException {
		//Act
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/7measurements/111"),
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
	public void testGet7MeasurementsNoContent() throws JSONException {	
		//Act
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/7measurements/999"),
				HttpMethod.GET, entity, String.class);
		
		//Assert
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
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
	public void testGet7MeasurementsOfWrongType() throws JSONException {
		//Act
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/7measurements/111/not_a_type_in_DB"),
				HttpMethod.GET, entity, String.class);
		
		//Assert
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	/*@Test
	public void testGetMeasurementsFromTo() throws JSONException {
	
	
		//String fromTo = "Sat%20Apr%2010%202021%2000:00:00%20GMT+0200%20(Centraleurop%C3%A6isk%20sommertid)/Thu%20Apr%2015%202021%2000:00:00%20GMT+0200%20(Centraleurop%C3%A6isk%20sommertid)";
		//String to = "Thu%20Apr%2015%202021%2000:00:00%20GMT+0200%20(Centraleurop%C3%A6isk%20sommertid)";
		//Assert
		String from = "2021-04-9T06:00:00.000+0000";
		String to = "2021-04-14T06:00:00.000+0000";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		Date fromd = null;
		Date tod = null;
		try {
			fromd = dateFormat.parse(from);
			tod = dateFormat.parse(to);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//Act
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/measurements/111/cnt_steps/"+fromd+"/"+tod),
				HttpMethod.GET, entity, String.class);
		
		//Assert
		System.out.println(measurementInDatabase.getDatepost());
		System.out.println(fromd);
		System.out.println(tod);
		System.out.println(response.getBody());
		
		
		Measurement gotMeasurement = gson.fromJson((new JSONArray(response.getBody())).getJSONObject(0).toString(), Measurement.class);
		System.out.println(gotMeasurement);
		assertEquals(measurementInDatabase.getMeasurementtype(), gotMeasurement.getMeasurementtype());
		assertEquals(measurementInDatabase.getPatientid(), gotMeasurement.getPatientid());
		assertEquals(measurementInDatabase.getMeasurementvalue(), gotMeasurement.getMeasurementvalue());
		assertEquals(measurementInDatabase.getDatepost(), gotMeasurement.getDatepost());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}*/
	
	
	
	//POst that already exists = 404.METHOD_NOT_ALLOWED
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port +"/api"+ uri;
	}
	
	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
				
		return cal.getTime();
	}
	
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
}
