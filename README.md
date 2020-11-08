# Prerequisite info 

```bash
The project was made using these technologies:
 1. Java 11
 2. SpringBoot
 3. Spring MVC
 4. Maven
 5. Docker
 6. MySQL DB
```


# How to run in Docker

```bash
Make sure you have docker-compose installed.
Clone the project.

From the root folder execute this command in terminal:
docker-compose up
```

# How to run for local development

```bash
In application.properties file change spring.datasource.url
from
spring.datasource.url=jdbc:mysql://fp-mysql:3306/user_database
to
spring.datasource.url=jdbc:mysql://localhost:3306/user_database

From the root folder execute this command in terminal:
mvn clean spring-boot:run 