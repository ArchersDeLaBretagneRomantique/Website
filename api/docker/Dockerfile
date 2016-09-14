FROM openjdk:8-jre

MAINTAINER Benoit TRAVERS <benoit.travers.fr@gmail.com>

VOLUME ["/tmp", "/photos"]

COPY target/abr-api.jar app.jar

RUN sh -c 'touch /app.jar'

ENTRYPOINT ["java", "-Xmx1024M", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar", "--spring.profiles.active=prod"]
