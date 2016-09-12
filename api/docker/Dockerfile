FROM java:8-jre

VOLUME ["/tmp", "/photos"]

COPY abr-api.jar app.jar

RUN sh -c 'touch /app.jar'

ENTRYPOINT ["java", "-Xmx1024M", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar", "--spring.profiles.active=prod"]
