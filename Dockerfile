FROM openjdk:17
COPY  target/docker-core.jar docker-core.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","docker-core.jar"]
