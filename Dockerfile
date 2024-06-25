FROM eclipse-temurin:19-jre-jammy

WORKDIR .

COPY target/*-SNAPSHOT.jar bookstore-app.jar

EXPOSE 8080

CMD ["java", "-jar", "bookstore-app.jar"]