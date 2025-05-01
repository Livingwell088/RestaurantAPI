
# Use a base image with Java 17
FROM openjdk:17-jdk-slim

# Copy the built jar file into the image
#COPY build/libs/*.jar app.jar
#
# Set the entry point to run your application
#ENTRYPOINT ["java","-jar","/app.jar"]

#FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]