FROM maven:3.9.2-eclipse-temurin-22 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests



FROM openjdk:22-jdk
WORKDIR /app
COPY --from=build /app/target/edumate_backend.jar edumate_backend.jar
#ADD target/edumate_backend.jar edumate_backend.jar
ENTRYPOINT [ "java", "-jar", "edumate_backend.jar" ]