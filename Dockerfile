FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} sicredi-voting.jar
ENTRYPOINT ["java", "-jar", "sicredi-voting.jar"]