FROM openjdk:11
VOLUME /tmp
ADD target/fleet-management-service-10.0.1.jar fleet-management-service.jar
EXPOSE 8502
ENV JAVA_OPTS="-Dfile.encoding=UTF8 -Djava.awt.headless=true -Xmx2g"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /fleet-management-service.jar --server.port=8502" ]