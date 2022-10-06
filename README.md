# Simple-Migration
It is simple application to migrate data.

## Getting Started
### Prerequisites
* Java 17
* Docker

### Starting application
* Run Dummy-Data-Service and data will be created into csv folder inside of project.
* Build image for Transform-Service. 
  * Navigate to Simple-Migration and run next command in terminal
```
docker build -f Dockerfile-transform -t transform-service .
```
* Build image for Profile-Service (API). 
  * Navigate to Simple-Migration and run next command in terminal
```
docker build -f Dockerfile-profile -t profile-service .
```
* To start applicaiton go to docker file and run next command in terminal
```
docker-compose up
```

### Stop application
* go to docker file and run next command in terminal
```
docker-compose down
```
