FROM java:8-jre
MAINTAINER Kurus Artem <Akurus@gmail.com>

ADD ./target/telegram-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/telegram-service.jar"]

EXPOSE 6000