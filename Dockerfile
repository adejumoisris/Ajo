# Use Ubuntu as the base image
FROM ubuntu:20.04 AS build 

# Install OpenJDK 11 and Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Set the working directory inside the container
#WORKDIR /app
COPY . .
RUN mvn clean install
#FROM openjdk-17-jdk
EXPOSE 2020
COPY —from-build build/libs/Ajo-0.0.1-SNAPSHOT.jar ajo.jar
ENTRYPOINT  ["java", "-jar", “ajo.jar”]
