package com.bachelor.Heartapp;

import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import com.bachelor.Heartapp.controller.RealTimeController;
import com.bachelor.Heartapp.model.DailyRecording;
import com.bachelor.Heartapp.model.Measurement;
import com.bachelor.Heartapp.model.RealTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.BaseEncoding;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@EnableSwagger2
public class HeartAppApplication {
	
//	public final static String backendUrlRoot = "http://165.22.31.227:8081";
	public final static String backendUrlRoot = "http://localhost:8081";	
	public final static String measurementTableName = "patient_data_1";
	public final static String realtimeTableName = "ecg";
	
	@Autowired
	MeasurementController measurementController;
	
	@Autowired
	RealTimeController realtimeController;
	
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
			/*System.out.println("Start getting data into local database");
			ResponseEntity<Object[]> responseEntity =
					   restTemplate.getForEntity("http://167.99.133.167:54532/all", Object[].class);
			
			Object[] objects = responseEntity.getBody();

			ObjectMapper mapper = new ObjectMapper();
			
			List<DailyRecording> dr = Arrays.stream(objects)
					  .map(object -> mapper.convertValue(object, DailyRecording.class))
					  .collect(Collectors.toList());

			dr.forEach(d -> transform(d)); 
			System.out.println("DONE!");*/
			
		//	postMockECG();
		};
	}
	
	public void transform(DailyRecording d) {
		measurementController.createMeasurement(new Measurement(d.getPatient_id(), d.getDate_post(),"blood_pressure_diastolic",d.getBlood_pressure_diastolic().toString()));
		measurementController.createMeasurement(new Measurement(d.getPatient_id(), d.getDate_post(),"blood_pressure_systolic",d.getBlood_pressure_systolic().toString()));
		measurementController.createMeasurement(new Measurement(d.getPatient_id(), d.getDate_post(), "cnt_steps" ,d.getCnt_steps().toString()));
		measurementController.createMeasurement(new Measurement(d.getPatient_id(), d.getDate_post(),"sleep_deep",Float.toString(d.getSleep_deep())));
		measurementController.createMeasurement(new Measurement(d.getPatient_id(), d.getDate_post(),"sleep_light",Float.toString(d.getSleep_light())));
		measurementController.createMeasurement(new Measurement(d.getPatient_id(), d.getDate_post(),"sleep_rem",Float.toString(d.getSleep_rem())));	
		
		count++;
	}
	
	public void postMockECG() {
		try {
			while (true) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				
				byte[] dataByte = BaseEncoding.base16().lowerCase().decode("2B0116025102330212020D0212021602140214021402110210021002F801E401E101E001E201E001DD01DD01D901DA01D601D301D201CD01CB01C601BF01BB01B401B001AA01A401A401A301A801AF01B901CA01DA01EC01FA0104020E020F0212021102100210020D020E020D020C020D020A020C020B0209020A0207020902080206020802060207020802070209020602070206020602070205020602050201020102FC01FD01FE010002050204020502040202020402020203020402FE01F601E601DB01D601F60121022F021702F501E201E301E201E301E101DE01DF01DB01DB01D901D601D601D201D101CD01C801C501BE01BB01B701B4010000".toLowerCase());
//				byte[] dataLong = {0xC, 0x2, 0xDA, 0x3, 0x65, 0x4, 0x37, 0x4, 0xFC, 0x3, 0xEE, 0x3, 0xF7, 0x3, 0x0, 0x4, 0x2, 0x4, 0x1, 0x4, 0xFF, 0x3, 0xFF, 0x3, 0xFF, 0x3, 0x0, 0x4, 0x32, 0x3, 0xF9, 0x1, 0xAD, 0x1, 0xF7, 0x1, 0xCA, 0x1, 0x22, 0x2, 0xEB, 0x1, 0xE9, 0x1, 0x16, 0x2, 0xC7, 0x1, 0x17, 0x2, 0xE7, 0x1, 0xE9, 0x1, 0x18, 0x2, 0xC7, 0x1, 0x17, 0x2, 0xE7, 0x1, 0xEA, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x18, 0x2, 0xE7, 0x1, 0xE9, 0x1, 0x18, 0x2, 0xC8, 0x1, 0x18, 0x2, 0xE7, 0x1, 0xE9, 0x1, 0x18, 0x2, 0xC7, 0x1, 0x17, 0x2, 0xE7, 0x1, 0xE9, 0x1, 0x18, 0x2, 0xC7, 0x1, 0x17, 0x2, 0xE8, 0x1, 0xE8, 0x1, 0x18, 0x2, 0xC7, 0x1, 0x17, 0x2, 0xE7, 0x1, 0xE9, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE8, 0x1, 0xE8, 0x1, 0x18, 0x2, 0xC7, 0x1, 0x17, 0x2, 0xE8, 0x1, 0xE8, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE8, 0x1, 0xE8, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE8, 0x1, 0xE7, 0x1, 0x18, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE8, 0x1, 0xE7, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE9, 0x1, 0xE7, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE8, 0x1, 0xE6, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE9, 0x1, 0xE7, 0x1, 0x19, 0x2, 0xC9, 0x1, 0x17, 0x2, 0xE9, 0x1, 0xE6, 0x1, 0x1A, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE9, 0x1, 0xE7, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xE9, 0x1, 0xE5, 0x1, 0x19, 0x2, 0xC9, 0x1, 0x17, 0x2, 0xEA, 0x1, 0xE6, 0x1, 0x19, 0x2, 0xC9, 0x1, 0x17, 0x2, 0xE9, 0x1, 0xE4, 0x1, 0x19, 0x2, 0xC8, 0x1, 0x17, 0x2, 0xEA, 0x1, 0x5C, 0x0};

				//System.arraycopy(dataLong, 0, data, 0, 124);				
				Date timestamp = new Date(System.currentTimeMillis());

//				realtimeController.createRealTime(new RealTime("010101-1234", timestamp, dataByte));
				
				System.out.println("ecg post"); 
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
