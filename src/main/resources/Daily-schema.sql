CREATE TABLE [IF NOT EXITS] daily  (
	patient_id VARCHAR(50) NOT NULL PRIMARY KEY,	
	blood_pressure_diastolic INT NOT NULL,
	blood_pressure_systolic INT NOT NULL
);