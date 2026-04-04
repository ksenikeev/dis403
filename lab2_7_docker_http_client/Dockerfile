# Dockerfile

FROM eclipse-temurin:17

WORKDIR /app

COPY target/lab2_7_docker_http_client-1.0-SNAPSHOT.jar /app/target/lab2_7_docker_http_client-1.0-SNAPSHOT.jar
#COPY pom.xml ./
#COPY src/ ./src/
#RUN mvn package
#CMD ["java", "-version"]
CMD ["java", "-jar", "/app/target/lab2_7_docker_http_client-1.0-SNAPSHOT.jar"]