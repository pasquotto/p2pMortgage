FROM frolvlad/alpine-oraclejdk8:slim
#DOCKER_CERT_PATH=/Users/development/.docker/machine/certs/cert.pem
#DOCKER_HOST=tcp://192.168.99.100:2376
#DOCKER_TLS_VERIFY=1
VOLUME /tmp
ADD mortgage-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]