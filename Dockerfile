FROM openjdk:17-jdk-slim
WORKDIR /beatflow-back
COPY target/beatflow-0.0.1-SNAPSHOT.jar /beatflow-back
EXPOSE 8081
CMD ["java", "-jar", "beatflow-0.0.1-SNAPSHOT.jar"]

#INSTALACION
#docker build -t login:latest .
#docker run -d --name login-container -p 8080:8080 login:latest