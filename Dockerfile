FROM maven:3.6.3-adoptopenjdk-14 AS build-env

COPY . /home/app
WORKDIR /home/app

RUN mvn -ntp clean install package -DskipTests


FROM openjdk:11-jre-slim

COPY --from=build-env /home/app/target/*.war app.war

EXPOSE 80

CMD ["java", "-jar", "app.war"]