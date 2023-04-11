FROM openjdk:11-jre-slim

WORKDIR /customer

COPY target/*.jar /customer/app.jar

EXPOSE 8080

CMD java -XX:+UseContainerSupport -jar app.jar

