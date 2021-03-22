package com.bachelor.Heartapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
			System.out.println("Heeeeej");
			ResponseEntity<Object[]> responseEntity =
					   restTemplate.getForEntity("http://167.99.133.167:54532/all", Object[].class);
			
			Object[] objects = responseEntity.getBody();
			
			ObjectMapper mapper = new ObjectMapper();
			
			List<DailyRecording> dr = Arrays.stream(objects)
					  .map(object -> mapper.convertValue(object, DailyRecording.class))
					  .collect(Collectors.toList());
			System.out.println(objects[0]);
			dr.forEach(d -> transform(d));
			System.out.println("DONE!!!!!");

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


}
