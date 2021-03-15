package com.bachelor.Heartapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.client.RestTemplate;

import com.bachelor.Heartapp.model.DailyRecording;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.LoggerFactory;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class HeartAppApplication {

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
			ResponseEntity<Object[]> responseEntity =
					   restTemplate.getForEntity("http://167.99.133.167:54532/all", Object[].class);
			
			Object[] objects = responseEntity.getBody();
			
			ObjectMapper mapper = new ObjectMapper();
			
			List<DailyRecording> dr = Arrays.stream(objects)
					  .map(object -> mapper.convertValue(object, DailyRecording.class))
			//		  .map(DailyRecording::getDate_post)
					  .collect(Collectors.toList());
			
			System.out.print(dr.get(1));
		};
	}

}