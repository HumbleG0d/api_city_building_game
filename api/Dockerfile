# Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .  

# Download dependencies without running tests
RUN mvn dependency:go-offline

COPY src ./src  

# Build the application, skipping tests for faster builds
RUN mvn clean package -DskipTests

# Production stage
FROM amazoncorretto:17
ARG PROFILE=dev  # Default profile is 'dev'
ARG APP_VERSION=0.0.1-SNAPSHOT  # Default application version

WORKDIR /app  # Set working directory for the production image

# Copy the built jar file from the build stage
COPY --from=build /build/target/api-*.jar /app/city_game.jar

# Expose port 8081 for the application
EXPOSE 8081

# Set environment variables for database URL and application profile
ENV DB_URL=jdbc:postgresql://postgres-sql:5432/city_game_db
ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}

# Command to run the application with debugging enabled
CMD java -jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -Dspring.profiles.active=${ACTIVE_PROFILE} -Dspring.datasource.url=${DB_URL} city_game.jar