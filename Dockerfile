FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/academic-blog-api-0.0.1-SNAPSHOT.jar academicblogapi.jar
EXPOSE 27017
ENTRYPOINT exec java $JAVA_OPTS -jar academicblogapi.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar academicblogapi.jar
