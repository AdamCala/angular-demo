# Stage 1: Build the Spring Boot application with Gradle
FROM eclipse-temurin:24-jdk AS builder
WORKDIR /app

# Copy the Gradle wrapper files
COPY gradlew .
COPY gradle ./gradle

# Copy the build configuration files
COPY build.gradle .
COPY settings.gradle .

# This first build step downloads dependencies. It's a separate layer, so it's cached
# unless the build.gradle or settings.gradle file changes.
RUN ./gradlew build --no-daemon > /dev/null 2>&1 || true

# Copy the source code
COPY src ./src

# Build the application
RUN ./gradlew build --no-daemon

# Stage 2: Create the final, smaller image using a JRE
FROM eclipse-temurin:24-jre
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Use the standard 'java -jar' command to run the Spring Boot executable JAR.
ENTRYPOINT ["java", "-jar", "app.jar"]

