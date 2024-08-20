# Build stage
FROM maven:3.9.9-eclipse-temurin-22-jammy AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Run stage
FROM eclipse-temurin:22-jre-jammy
WORKDIR /app

# Create a non-root user and group
RUN groupadd -r appgroup && useradd -r -g appgroup appuser

#COPY --from=build /app/target .
COPY --from=build /app/target/basicStory-23.9.2-SNAPSHOT-jar-with-dependencies.jar .

# Set the owner of the application to the non-root user
RUN chown -R appuser:appgroup /app

# Switch to the non-root user
USER appuser

CMD ["java", "-jar", "basicStory-23.9.2-SNAPSHOT-jar-with-dependencies.jar"]