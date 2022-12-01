FROM molgenis/maven-jdk17
COPY ./ ./
RUN (cd ./common-module && mvn clean install)
RUN (cd ../web && mvn clean package -DskipTests)
ENTRYPOINT ["java","-jar","web/target/web-0.0.1-SNAPSHOT.war"]


