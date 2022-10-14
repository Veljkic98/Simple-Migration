FROM openjdk:17

EXPOSE 8080

ADD target/TransformService-0.0.1-SNAPSHOT.jar TransformService-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java", "-Dspring.profiles.active=prod", "-jar", "/TransformService-0.0.1-SNAPSHOT.jar" ]