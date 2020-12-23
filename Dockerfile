FROM openjdk:8-alpine

COPY target/vorstellung.jar /vorstellung/app.jar

EXPOSE 3000

CMD ["java", "-cp", "/vorstellung/app.jar", "clojure.main", "vorstellung.core"]
