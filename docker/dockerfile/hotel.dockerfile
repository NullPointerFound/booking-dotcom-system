FROM maven:3.9.4-amazoncorretto-21 as builder

WORKDIR /application

COPY ./ /application

RUN mvn clean package

FROM amazoncorretto:21

WORKDIR /service

COPY --from=builder /application/hotel-service/target/*.jar /service/api.jar
ENV TZ=America/New_York

ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Duser.region=US -Duser.language=en ${JAVA_OPTS} -jar /service/api.jar" ]
