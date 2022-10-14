# Energy Consumption
It is simple application to store, retrieve and power measure data.

## Getting Started
### Prerequisites
* Java 17
* Docker

### Starting application with prod profile

* Build Jar for Transform-Service. If you want to skip tests add `-DskipTests`
```
./mvnw clean install
```
* Build image for Transform-Service (API). 
```
docker build -f Dockerfile -t profile-service .
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
### If this is not for the purpose od use case, following steps would be applied:
- Add Authentication and Authorization (OAuth2 - custom or Google's)
- In case it is necessary to save files, than upload data files on bucket (on local env it could be Minio, on prod env AWS S3 for example)
- Increase test coverage of Integration and Unit tests at least to 80%
- Additionaly Configure where to write logs and present them through ELK stack
- Cover all controllers with Swagger documentation
- Implement generic Builder pattern
- Use lombok instead of geters, setters, constructors and builders
- Write proper javaDoc comments

