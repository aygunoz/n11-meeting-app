
![apply-talk-light.png](readme-pictures%2Fapply-talk-light.png)
![apply-talk.png](readme-pictures%2Fapply-talk.png)
![ligth-conference.png](readme-pictures%2Fligth-conference.png)
![dark-conference.png](readme-pictures%2Fdark-conference.png)
This project is written with both frontend and backend combined. The 'main' folder under the './src' directory contains backend files, while the 'react-webapp' section encompasses the frontend. Additionally, there are tests under the 'test' folder.

#### Technology Stack
* Vite (5.0.12) + React (18.2.0) + Nodejs (>= 14.x)
* Nodejs
* Java (21)
* Spring Boot
* Spring Data JPA
* Spring MVC
* H2 embedded database


**How to run the application**

1. Run following command to download javascript dependencies:

       npm install

2. Use following commands to run the application. The project is served from `http://localhost:8080`:

       mvn spring-boot:run
       npm run dev

3. You can check all API documents Swagger below link after started project;

       http://localhost:8080/swagger-ui/index.html#/

4. You can connect to h2 database below link with user credentials

       http://localhost:8080/h2-console
       JDBC URL: jdbc:h2:mem:testdb 
       User Name: sa 
       Password: password
