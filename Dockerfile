FROM openjdk:11
COPY build/libs/gaza-server:0.0.1 app.jar
EXPOSE 8080
ENV JAR_PATH=/app/build/libs
