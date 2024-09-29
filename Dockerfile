FROM openjdk:21
#as build
USER root

COPY src/main/resources/deploy_application.yml application.yml
COPY target/microservice.graalvm-1.0.jar /microservice.graalvm-1.0.jar

#FROM oraclelinux:9

ENTRYPOINT ["sh","-c","java -Duser.timezone=Asia/Bangkok -Dspring.config.location=application.yml -jar microservice.graalvm-1.0.jar"]