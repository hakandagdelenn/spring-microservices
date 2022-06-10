FROM openjdk:11
VOLUME /tmp
ADD target/eureka-server-10.0.1.jar eurekaserver.jar
EXPOSE 8500
ENV JAVA_OPTS="-Dfile.encoding=UTF8 -Djava.awt.headless=true -Xmx2g"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /eurekaserver.jar --server.port=8500" ]