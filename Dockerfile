# === STAGE 1: Build the application ===
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the project source from the 'library' folder
COPY library/pom.xml .
COPY library/src ./src

# Build the Spring Boot JAR
RUN mvn clean package -DskipTests

# === STAGE 2: Run the application ===
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
