FROM gradle:jdk21

WORKDIR /app

COPY . /app

RUN chmod +x gradlew

RUN ./gradlew clean build

EXPOSE 8080

CMD ["java", "-jar", "build/libs/your-app.jar"]
