
# Use a base image with Java 17
#FROM openjdk:17-jdk-slim
#
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

# Use a base image with Java 17
#FROM openjdk:17-jdk-alpine
FROM openjdk:17
# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Expose the port the app runs on (default Spring Boot port is 8080)
EXPOSE 8080

# Command to execute the application
ENTRYPOINT ["java", "-jar", "app.jar"]