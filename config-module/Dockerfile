FROM openjdk:11
VOLUME /tmp
ADD target/config-module-10.0.1.jar configmodule.jar
EXPOSE 8888
ENV JAVA_OPTS="-Dfile.encoding=UTF8 -Djava.awt.headless=true -Xmx2g"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /configmodule.jar --server.port=8888" ]