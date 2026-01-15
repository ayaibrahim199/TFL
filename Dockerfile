# Build stage
# We use the standard Temurin 25 image and install Maven manually to be safe
FROM eclipse-temurin:25-jdk AS build
RUN apt-get update && apt-get install -y maven
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:25-jre
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]