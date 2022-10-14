# Simple-Migration
It is simple application to migrate data.

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
### If this is real application, following steps will be applied:
- Add Authentication and Authorization (OAuth2 - custom or Google's)
- Upload Data files on bucket (on local env it could be Minio, on prod env AWS S3 for example)
- Write rest of tests
- configure where to write logs and present them through ELK stack
- Add swagger documentation for rest of controllers
- Create generic Builder
- Use lombok instead of geters, setters, constructors and builders
- Write proper javaDoc

