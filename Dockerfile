FROM eclipse-temurin:21-jre

WORKDIR /src

COPY target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]

