# Use an official OpenJDK image as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper files and the pom.xml
COPY ./mvnw ./mvnw
COPY ./mvnw.cmd ./mvnw.cmd
COPY ./.mvn ./.mvn
COPY ./pom.xml ./pom.xml

# Copy the project files
COPY ./src ./src

# Expose the port on which the Spring Boot app will run
EXPOSE 8080

# Build the application
RUN ./mvnw package -DskipTests

# Run the application
CMD ["java", "-jar", "./target/MVP-0.0.1-SNAPSHOT.jar"]
