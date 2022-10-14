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
- create generic Builder
