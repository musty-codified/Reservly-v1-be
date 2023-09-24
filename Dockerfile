
FROM eclipse-temurin:17

LABEL mentor="ilemonamustapha@gmail.com"

WORKDIR /app

COPY target/Reservly-v1-be-0.0.1-SNAPSHOT.jar /app/Reservly-v1-be.jar

ENTRYPOINT ["java", "-jar", "Reservly-v1-be.jar"]