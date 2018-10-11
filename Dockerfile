FROM openjdk:8-jdk

ADD build/libs/jenkins-sample-1.0.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]