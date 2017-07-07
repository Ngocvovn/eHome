FROM java:8
VOLUME /tmp
EXPOSE 8080
ADD target/BlackJack-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]