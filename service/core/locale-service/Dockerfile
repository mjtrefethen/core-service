FROM java:8
VOLUME /tmp
ADD build/libs/locale-service*.jar app.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Xdebug -Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]