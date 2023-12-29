# Use the official OpenJDK 17 base image
FROM adoptopenjdk/openjdk17:alpine-jre

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/your-spring-boot-app.jar /app/fullStackApp-0.0.1-SNAPSHOT.jar

# Expose the port that your Spring Boot application will run on
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "fullStackApp-0.0.1-SNAPSHOT.jar"]
