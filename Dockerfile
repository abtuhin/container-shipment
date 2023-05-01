# Use an OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim-buster

# Set the working directory to /app
WORKDIR /app

# Copy the executable JAR file and any other necessary files to the container
COPY target/container-shipment.jar /app

# Run the application when the container starts
CMD ["java", "-jar", "/app/container-shipment.jar"]
