FROM openjdk:8-jdk-alpine

COPY target/game-quake-0.0.1-SNAPSHOT.jar /tmp/app.jar
COPY games.log /tmp/games.log

ENTRYPOINT ["java","-jar","/tmp/app.jar"]
