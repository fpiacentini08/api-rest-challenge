FROM openjdk:17
WORKDIR /tmp

COPY ./target/challenge-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["bash", "-c","exec java -jar ./challenge-0.0.1-SNAPSHOT.jar"]
