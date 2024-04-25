FROM openjdk:17-jdk-slim

ARG profile
ENV profile=${profile}

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.profiles.active=${profile}"]
