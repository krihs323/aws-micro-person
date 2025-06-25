FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
EXPOSE 8080
ADD ./build/libs/personas-0.0.1-SNAPSHOT.jar personas.jar
ENTRYPOINT ["java", "-jar", "personas.jar"]