FROM adoptopenjdk:11-jre-hotspot
ENV TZ America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN mkdir /opt/sicredivoting
COPY target/sicredi-resultmq-service-1.0.0.jar /opt/sicredivoting/sicredi-resultmq-service.jar
CMD ["java", "-Duser.timezone='America/Sao_Paulo'", "-jar", "/opt/sicredivoting/sicredi-resultmq-service.jar"]