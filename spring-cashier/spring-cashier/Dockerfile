# Use the official maven/Java 11 image to create a build artifact.
FROM maven:3.8.3-openjdk-11-slim AS build

# Set the working directory.
WORKDIR /usr/src/app

# Copy the pom.xml file to download dependencies.
COPY pom.xml .

# Download dependencies.
RUN mvn dependency:go-offline

# Copy the rest of the application source code.
COPY src ./src

# Build the application.
RUN mvn package

# Use the official openjdk image as the base image.
FROM openjdk:11-jre-slim

# Set the working directory.
WORKDIR /app

# Copy the JAR file from the build stage.
COPY --from=build /usr/src/app/target/spring-cashier-*.jar /app/spring-cashier.jar

# Expose the application's port.
EXPOSE 8080

# Run the application.
CMD ["java", "-jar", "/app/spring-cashier.jar"]