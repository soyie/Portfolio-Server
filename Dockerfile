# Use the official OpenJDK 17 base image from Eclipse Temurin
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app



# Copy the JAR file into the container at /app
COPY target/your-spring-boot-app.jar /app/fullStackApp-0.0.1-SNAPSHOT.jar

BUILD -t eclipse-temurin -f /Portfolio-Server/Dockerfile /target

# Expose the port that your Spring Boot application will run on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "fullStackApp-0.0.1-SNAPSHOT.jar"]
