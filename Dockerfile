FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/*.jar app.jar
ADD ./start.sh start.sh
ENTRYPOINT ["/bin/sh", "./start.sh"]
#ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]