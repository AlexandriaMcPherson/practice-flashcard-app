# syntax=docker/dockerfile:1

# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the compiled Spring Boot JAR file into the container
COPY target/flashcardapp-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot application will run on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]