FROM openjdk:17
ADD ./target/reservly.jar reservly.jar
ENTRYPOINT ["java", "-jar", "reservly.jar"]
EXPOSE 8080