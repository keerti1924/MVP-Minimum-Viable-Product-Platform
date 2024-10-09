# Use an official OpenJDK image as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper files and the pom.xml
COPY ./mvnw ./mvnw
COPY ./mvnw.cmd ./mvnw.cmd
COPY ./.mvn ./.mvn
COPY ./pom.xml ./pom.xml

# Copy the source code
COPY ./src ./src

# Expose the port on which the Spring Boot app will run
EXPOSE 8080

# Ensure the Maven wrapper is executable
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Run the application
CMD ["java", "-jar", "./target/MVP-0.0.1-SNAPSHOT.jar"]
