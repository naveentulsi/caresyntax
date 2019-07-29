# caresyntax - Study Scheduling Application

caresyntax ssa is procedure scheduling application.

## Installation

- Maven 3.2.5
- Oracle jdk 1.8

## Usage

### What's inside 
This project is based on the [Spring Boot](http://projects.spring.io/spring-boot/) project and uses these packages :
  - Maven
  - Spring Core
  - Spring Data (Hibernate & Postgres)
  - Embedded Tomcat 9
  - Angular Web App

Testing
  - JUnit
  - Mockito

Angular documentation
App is located as a module in parent spring boot project
    

## Installation 
The project is created with Maven, so you just need to build the project to resolve the dependencies
## Usage
    ```
    mvn clean install
    ```

To see the test coverage 

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

## Usage 
Run the project through the mvn and head out to [http://localhost:8080/ssa](http://localhost:8080/ssa)

run this command in the command line:
```
mvn spring-boot:run
```
