FROM openjdk:17-alpine3.14
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} distributed-comp-lab-9.jar
ENTRYPOINT ["java","-jar","/distributed-comp-lab-9.jar"]