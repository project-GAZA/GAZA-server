FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
RUN ECHO  "JAR_FILE : " $JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENV JAR_PATH=/app/build/libs
ENTRYPOINT ["java","-jar","-Duser.timezone=Asia/Seoul","/app.jar"]
