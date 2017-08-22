FROM openjdk:8-jre-alpine

ENV JHIPSTER_SLEEP 0

# add directly the war
ADD ./webui/target/*.war /app.war

RUN sh -c 'touch /app.war'
VOLUME /tmp
CMD echo "The application will start in ${JHIPSTER_SLEEP}s..." && \
    sleep ${JHIPSTER_SLEEP} && \
    java -Djava.security.egd=file:/dev/./urandom -jar -Xmx300m /app.war
