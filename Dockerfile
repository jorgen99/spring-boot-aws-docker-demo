#FROM adoptopenjdk/openjdk11:alpine
FROM amazoncorretto:11
COPY ./target/demo-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
