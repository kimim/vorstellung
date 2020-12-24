FROM openjdk:8-alpine

COPY target/vorstellung.jar /vorstellung/app.jar

EXPOSE 3000

ENV DATABASE_URL="jdbc:sqlite:vorstellung_dev.db"

CMD ["java", "-cp", "/vorstellung/app.jar", "clojure.main", "-m", "vorstellung.core"]
