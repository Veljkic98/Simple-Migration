FROM openjdk:17

EXPOSE 8082

ADD target/ProfileService-0.0.1-SNAPSHOT.jar ProfileService-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java", "-Dspring.profiles.active=prod", "-jar", "/ProfileService-0.0.1-SNAPSHOT.jar" ]