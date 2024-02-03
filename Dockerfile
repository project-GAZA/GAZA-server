FROM openjdk:11
COPY build/libs/GAZA-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENV JAR_PATH=/app/build/libs
