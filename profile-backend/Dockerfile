FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/app.jar app.jar
COPY db db
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=cloud", "-jar","/app.jar"]
