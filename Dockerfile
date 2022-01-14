FROM openjdk:8-jdk-alpine
EXPOSE 8082
ARG JAR_FILE=build/libs/filestorage-app.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]