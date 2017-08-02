FROM maven

WORKDIR /code
ADD pom.xml /code/pom.xml
ADD src /code/src
RUN mvn dependency:resolve
RUN mvn clean install

EXPOSE 8080
CMD java -jar target/xy-inc-0.0.1-SNAPSHOT.jar