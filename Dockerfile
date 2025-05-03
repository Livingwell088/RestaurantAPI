# Use a base image with Java 17
FROM openjdk:21

# Set the working directory inside the container
#WORKDIR /app

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Expose the port the app runs on (default Spring Boot port is 8080)
#EXPOSE 8080

# Command to execute the application
ENTRYPOINT ["java", "-jar", "app.jar"]


#FROM maven:4.0.0-jdk-17 AS builder
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package -DskipTests

#FROM openjdk:21
#WORKDIR /app
#COPY --from=builder /app/target/*.jar /app/my-app.jar
#CMD ["java", "-jar", "my-app.jar"]