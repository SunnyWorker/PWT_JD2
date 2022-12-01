FROM molgenis/maven-jdk17
COPY ./ ./
RUN mvn clean package -DskipTests
ENTRYPOINT ["java","-jar","target/EventWorker-1.0.0.war"]


