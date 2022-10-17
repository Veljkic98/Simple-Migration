FROM openjdk:17

EXPOSE 8080

ADD target/EnergyConsumption-0.0.1-SNAPSHOT.jar EnergyConsumption-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java", "-Dspring.profiles.active=prod", "-jar", "/EnergyConsumption-0.0.1-SNAPSHOT.jar" ]