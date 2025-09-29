# --- Stage 1: Build the application ---
# Use an official Maven image to build the project.
FROM maven:3.8-openjdk-17 AS build

# Set the working directory inside the container.
WORKDIR /app

# Copy the pom.xml and download dependencies.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code and build the JAR file.
COPY src ./src
RUN mvn clean install -DskipTests


# --- Stage 2: Create the final, smaller image ---
# Use a lightweight Java 17 image for the final container.
FROM openjdk:17-jdk-slim

# Set the working directory.
WORKDIR /app

# Copy only the built JAR file from the 'build' stage.
COPY --from=build /app/target/*.jar app.jar

# Expose the port your application runs on.
EXPOSE 8080

# The command to run your application when the container starts.
ENTRYPOINT ["java", "-jar", "app.jar"]