# # Use Ubuntu as the base image
# FROM ubuntu:20.04 AS build 

# # Install OpenJDK 11 and Maven
# RUN apt-get update && \
#     apt-get install -y openjdk-17-jdk maven

# # Set the working directory inside the container
# #WORKDIR /app
# COPY . .
# RUN mvn clean install
# #FROM openjdk-17-jdk
# EXPOSE 2020
# COPY —from-build build/libs/Ajo-0.0.1-SNAPSHOT.jar ajo.jar
# ENTRYPOINT  ["java", "-jar", “ajo.jar”]


FROM maven:3.8.4-openjdk-17 AS maven_dependencies
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B

FROM maven_dependencies AS maven_build
COPY . .
RUN mvn package -DskipTests -B

#FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.5_10
FROM amazoncorretto:17-alpine
ARG JAR_FILE=target/AjoPay-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY --from=maven_build /app/${JAR_FILE} .



EXPOSE ${PORT}

ENTRYPOINT ["java", "-jar", "AjoPay-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]
