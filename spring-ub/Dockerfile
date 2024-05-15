FROM openjdk:23-jdk-oracle
ADD build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 9001