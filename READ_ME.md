**Installation of project:**
1. Download the zip-file with the project
2. Install Maven
3. Install Node JS
4. Install PostgreSQL
5. Navigate to /Heart-app/src/main/resources and open the file application.properties and set the chosen settings for the postgreSQL database
6. In the root of the Heart-app folder, install dependencies by doing:  mvn package
7. Navigate to Heart-app/vue-material-dashboard-master folder and do: npm install

**Run the project:**
1. Open two terminals.
2. In first terminal:  In the Heart-app folder, do:   mvn spring-boot:run
3. In second terminal:  In the Heart-app/vue-material-dashboard-master folder, do:   npm run serve
4. The project will now be running on http://localhost:8081/
