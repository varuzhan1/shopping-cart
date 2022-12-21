FROM maven:3.8.5-eclipse-temurin-17
EXPOSE 80
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn clean install -DskipTests
CMD ["java", "-jar", "target/Shopping-Cart-0.0.1-SNAPSHOT.jar"]
