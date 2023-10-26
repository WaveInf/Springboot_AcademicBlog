# FROM openjdk:17-jdk-alpine
# VOLUME /tmp
# ARG JAVA_OPTS
# ENV JAVA_OPTS=$JAVA_OPTS
# COPY target/academic-blog-api-0.0.1-SNAPSHOT.jar academicblogapi.jar
# EXPOSE 27017
# # ENTRYPOINT exec java $JAVA_OPTS -jar academicblogapi.jar
# # For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
# ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar academicblogapi.jar

#
# Build stage
#
# docker pull maven:3.8.4-openjdk-17
FROM maven:3.8.4-openjdk-17 AS build
# WORKDIR /app
# COPY . /app/
COPY . .
RUN mvn clean package

#
# Package stage
#
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /target/academic-blog-api-1.0.0-SNAPSHOT.jar academic-blog-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","academic-blog-api-1.0.0-SNAPSHOT.jar"]
