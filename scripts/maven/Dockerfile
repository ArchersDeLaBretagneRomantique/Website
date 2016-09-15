FROM openjdk:8-jdk

MAINTAINER Benoit TRAVERS <benoit.travers.fr@gmail.com>

ARG MAVEN_VERSION=3.3.9
ARG USER_ID=1000
ARG USER_HOME_DIR="/home/username"

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
    | tar -xzC /usr/share/maven --strip-components=1 \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn \
  && adduser --disabled-login --gecos "" username --uid $USER_ID

VOLUME "$USER_HOME_DIR/.m2"

USER $USER_ID

CMD ["mvn"]
