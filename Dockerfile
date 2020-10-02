FROM openjdk:8-alpine

COPY target/uberjar/vorstellung.jar /vorstellung/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/vorstellung/app.jar"]
