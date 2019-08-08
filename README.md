# pse - Study Scheduling Application

An application to schedule procedures.

## Installation

- Maven - 3.2.5
- Oracle jdk - 1.8
- node js - 8.9.3
- npm - 5.10.0
- Angular CLI - 7.2.1 

### What's inside 
This project is based on the [Spring Boot](http://projects.spring.io/spring-boot/) and uses these packages :
  - Spring Core
  - Spring Data JPA (Hibernate & Postgres)
  - Embedded Tomcat 9
  - Angular 7
  - Rx Js 6.3

Testing
  - JUnit
  - Mockito
  - Spring MockMvc

### Angular documentation
Documentation to install and run Angular app is located at [ReadMe](https://github.com/naveentulsi/caresyntax/blob/master/ssa-ng-app/README.md)
as a module in parent spring boot project
    

## Build

### Angular 
To build angular app <br/>
Step 1: change dir to 'project-dir'/ssa-ng-app <br/>
Step 2: Dependency installation.<br/>

  ```
  npm install
  ```
Step 3: Build project using npm <br/>

  ```
  npm run build:aot
  ```
Step 4: Go back to project root dir 'project-dir' <br/>

### Spring boot
To build angular and spring boot togther, we are using org.codehaus.mojo plugin. Please make sure you have done the previous step. Otherwise next step would fail. <br/>
Build, test and package.<br/>

  ```
  mvn clean install
  ```
For integration testing H2 is being used, it is configured in test/respurces/applicaion.yml. To start test <br/>

  ```
  mvn test
  ```
Coverage report,<br/>
  
  ```
  mvn jacoco:report
  ```
## Database configuration 
Create a Postgres database and add the credentials as env variables with below keys as in `/resources/application.yml`.  
The default ones are :
```
spring:
  datasource:
    username: ${husername}
    password: ${hpassword}
    url: ${hdbUrl}
    driver-class-name: org.postgresql.Driver
```
<br/>
For testing an H2 in merory db is configured at `/test/resources/application.yml`.

## Run 
Run the project through mvn and head out to [http://localhost:8080/ssa](http://localhost:8080/ssa)

run this command in the command line:
```
mvn spring-boot:run
```
I have not provided a data.sql script to inject data in database.
But there is live demo with sample data hosted at:
[psa-application](http://ec2-18-222-221-79.us-east-2.compute.amazonaws.com:8080/ssa)

## Current Covearage report
![alt text](https://github.com/naveentulsi/caresyntax/blob/master/jacococoverage.png)



