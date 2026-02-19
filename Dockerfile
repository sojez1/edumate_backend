
FROM openjdk:22-jdk
ADD target/edumate_backend.jar edumate_backend.jar
ENTRYPOINT [ "java", "-jar", "edumate_backend.jar" ]