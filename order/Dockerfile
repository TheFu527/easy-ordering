FROM openjdk:11.0-oracle
WORKDIR app/
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar","app.jar"]
