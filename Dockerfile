FROM amazoncorretto:21

WORKDIR /app

COPY build/libs/*.jar nuevo_nombre_feeding.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "nuevo_nombre_feeding.jar"]