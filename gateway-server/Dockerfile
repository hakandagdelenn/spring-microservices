FROM openjdk:11
VOLUME /tmp
ADD target/gateway-server-10.0.1.jar gatewayserver.jar
EXPOSE 8501
ENV JAVA_OPTS="-Dfile.encoding=UTF8 -Djava.awt.headless=true -Xmx2g"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /gatewayserver.jar --server.port=8501" ]