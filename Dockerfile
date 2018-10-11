FROM openjdk:8-jdk

COPY /src/com/bnsf/jenkins/JenkinsSample.java /src/com/bnsf/jenkins/JenkinsSample.java
WORKDIR /src/com/bnsf/jenkins/
RUN javac JenkinsSample.java
CMD ["java", "JenkinsSample"]