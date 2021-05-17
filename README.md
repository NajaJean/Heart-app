# Heart-app
**Installation of project:**
1. Download the zip-file with the project
2. Install Maven
3. Install Node JS
4. Install PostgreSQL
5. Navigate to /Heart-app/src/main/resources and open the file application.properties and set the chosen settings for the postgreSQL database
6. In the root of the Heart-app folder, install dependencies by doing:  *mvn package*
7. Navigate to Heart-app/vue-material-dashboard-master folder and do: *npm install*
8. Inside your database (preferrably in SQL Shell) run the following command to create a table for ECG:
- CREATE TABLE ecg(id SERIAL, patient_id varchar(20) NOT NULL, ecg_data int4[], timestamp timestamp default current_timestamp);

**Run the project:**
1. Open two terminals.
2. In first terminal:  In the Heart-app folder, run back end by doing:   *mvn spring-boot:run*
3. In second terminal:  In the Heart-app/vue-material-dashboard-master folder, run front end by doing:   *npm run serve*
4. The project will now be running on http://localhost:8081/

**Run tests:**
- Integration tests: In the Heart-app folder, do:   *mvn test*
- API Unit tests and front end tests: run back and front end. In the Heart-app/vue-material-dashboard-master folder, do:  *npx cypress run*
