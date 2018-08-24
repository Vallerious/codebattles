FROM openjdk:8
ADD target/codebattles.jar codebattles.jar
EXPOSE 8080:8080
ENTRYPOINT ["java", "-jar", "codebattles.jar"]