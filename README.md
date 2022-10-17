# Energy Consumption
It is simple application to store, retrieve and power measure data.

## Getting Started
### Prerequisites
* Java 17
* Docker

### Starting application with prod profile

* Build Jar for Energy-Consumption. If you want to skip tests add `-DskipTests`
```
./mvnw clean install
```
* Build image for Energy-Consumption (API). 
```
docker build -f Dockerfile -t energy-consumption .
```

* To start applicaiton go to docker file and run next command in terminal
```
docker-compose up
```

### Stop application
* To stop and delete containers run next command in terminal
```
docker-compose down
```

### Starting application with local profile
Run next command
```
./mvnw spring-boot:run
```

## Future steps
### If this is not for the purpose of use case, following steps would be applied:
- Implement Authentication and Authorization (OAuth2 - custom or Google's)
- In case it is necessary to save files, than upload data files on some storage (on local env it could be Minio, on prod it could be AWS S3 for example)
- Implement SQL scripts for data migrations using Flayway or Liquibase
- Increase test coverage of Integration and Unit tests at least to 80%
- Additionally Configure where to write logs and present them through ELK stack
- Cover all controllers with Swagger documentation
- Implement generic Builder pattern or use lombok
- Use lombok instead of geters, setters and constructors
- Write proper javaDoc comments

