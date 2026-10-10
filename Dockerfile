FROM eclipse-temurin:21-jre

WORKDIR /src

COPY target/*.jar app.jar

CMD ["java", "-jar", "app.jar", "--spring.datasource.url=jdbc:sqlserver://host.docker.internal:1433;databaseName=HSF302_2026_PE_SDM;encrypt=true;trustServerCertificate=true"]

