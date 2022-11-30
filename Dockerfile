FROM openjdk:17
ADD target/EventWorker-1.0.0.war eventworker.war
ENTRYPOINT ["java","-jar","eventworker.war"]


