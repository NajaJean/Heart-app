package com.bachelor.Heartapp;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bachelor.Heartapp.controller.MeasurementController;
import com.bachelor.Heartapp.model.DailyRecording;
import com.bachelor.Heartapp.model.Measurement;
import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@EnableSwagger2
public class HeartAppApplication {
	
	@Autowired
	MeasurementController measurementController;
	
	int count = 0;

	public static void main(String[] args) {
		
		SpringApplication.run(HeartAppApplication.class, args);
	}
	

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			System.out.println("Start getting data into local database");
			ResponseEntity<Object[]> responseEntity =
					   restTemplate.getForEntity("http://167.99.133.167:54532/all", Object[].class);
			
			Object[] objects = responseEntity.getBody();

			ObjectMapper mapper = new ObjectMapper();
			
			List<DailyRecording> dr = Arrays.stream(objects)
					  .map(object -> mapper.convertValue(object, DailyRecording.class))
					  .collect(Collectors.toList());
			System.out.println(objects[0]);
	//		dr.forEach(d -> transform(d));
			System.out.println("DONE!");
			
			postMockECG();
		};
	}
	
	public void transform(DailyRecording d) {
		if (count%15==0) {
			measurementController.createMeasurement(new Measurement(d.getPatient_id(), d.getDate_post(),"blood_pressure_diastolic",d.getBlood_pressure_diastolic().toString()));
			measurementController.createMeasurement(new Measurement(d.getPatient_id(), d.getDate_post(),"blood_pressure_systolic",d.getBlood_pressure_systolic().toString()));
			measurementController.createMeasurement(new Measurement(d.getPatient_id(), d.getDate_post(), "cnt_steps" ,d.getCnt_steps().toString()));
			measurementController.createMeasurement(new Measurement(d.getPatient_id(), d.getDate_post(),"sleep_deep",Float.toString(d.getSleep_deep())));
			measurementController.createMeasurement(new Measurement(d.getPatient_id(), d.getDate_post(),"sleep_light",Float.toString(d.getSleep_light())));
			measurementController.createMeasurement(new Measurement(d.getPatient_id(), d.getDate_post(),"sleep_rem",Float.toString(d.getSleep_rem())));	
			
		}
		count++;
	}
	
	public void postMockECG() {
		try {
			while (true) {
				int[] data = {0xC, 0x2, 0xDA, 0x3, 0x65, 0x4, 0x37, 0x4, 0xFC, 0x3, 0xEE, 0x3, 0xF7, 0x3, 0x0, 0x4, 0x2, 0x4, 0x1, 0x4, 0xFF, 0x3, 0xFF, 0x3, 0xFF, 0x3, 0x0, 0x4, 0x32, 0x3, 0xF9, 0x1, 0xAD, 0x1, 0xF7, 0x1, 0xCA, 0x1, 0x22, 0x2, 0xEB, 0x1, 0xE9, 0x1, 0x16, 0x2, 0xC7, 0x1, 0x17, 0x2, 0xE7, 0x1, 0xE9, 0x1, 0x18, 0x2, 0xC7, 0x1, 0x17, 0x2, 0xE7, 0x1, 0xEA, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x18, 0x2, 0xE7, 0x1, 0xE9, 0x1, 0x18, 0x2, 0xC8, 0x1, 0x18, 0x2, 0xE7, 0x1, 0xE9, 0x1, 0x18, 0x2, 0xC7, 0x1, 0x17, 0x2, 0xE7, 0x1, 0xE9, 0x1, 0x18, 0x2, 0xC7, 0x1, 0x17, 0x2, 0xE8, 0x1, 0xE8, 0x1, 0x18, 0x2, 0xC7, 0x1, 0x17, 0x2, 0xE7, 0x1, 0xE9, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE8, 0x1, 0xE8, 0x1, 0x18, 0x2, 0xC7, 0x1, 0x17, 0x2, 0xE8, 0x1, 0xE8, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE8, 0x1, 0xE8, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE8, 0x1, 0xE7, 0x1, 0x18, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE8, 0x1, 0xE7, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE9, 0x1, 0xE7, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE8, 0x1, 0xE6, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE9, 0x1, 0xE7, 0x1, 0x19, 0x2, 0xC9, 0x1, 0x17, 0x2, 0xE9, 0x1, 0xE6, 0x1, 0x1A, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE9, 0x1, 0xE7, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE9, 0x1, 0xE5, 0x1, 0x19, 0x2, 0xC9, 0x1, 0x17, 0x2, 0xEA, 0x1, 0xE6, 0x1, 0x19, 0x2, 0xC9, 0x1, 0x17, 0x2, 0xE9, 0x1, 0xE4, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xEA, 0x1, 0x5C, 0x0};
				Date timestamp = new Date(System.currentTimeMillis());
				System.out.println("Data 0:" + data[3]);
				System.out.println("Timestamp: " + addSeconds(timestamp,1/data.length*3) );
				for (int i=0; i<data.length; i++) {
					measurementController.createMeasurement(new Measurement("101010-1234", addSeconds(timestamp,1/data.length*i).toString(), "EGC", String.valueOf(data[i])));
				}
				
				System.out.println("ecg post");
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static Date addSeconds(Date date, Integer seconds) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.MILLISECOND, seconds);
	    return cal.getTime();
	  }

}
