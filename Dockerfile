FROM maven:3.9.2-eclipse-temurin-17-alpine as BUILD

COPY pom.xml /build/pom.xml
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY --from=BUILD /build/target/*.jar /app/app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]