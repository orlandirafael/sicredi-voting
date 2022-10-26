FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} voting.jar
ENTRYPOINT ["java", "-jar", "voting.jar"]