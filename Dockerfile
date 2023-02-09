FROM cloudunder/java-runtime:openjdk8
WORKDIR /downwardapi-test
COPY ./target/downwardapi_test-1.0-SNAPSHOT-jar-with-dependencies.jar ./

## install JRE
#ENV JAVA_HOME=/usr/lib/jre
#ENV PATH=${PATH}:${JAVA_HOME}/bin
#RUN apt-get update \
#    && apt-get install -y wget \
#    && wget -nv -O jre.tar.gz "https://github.com/adoptium/temurin8-binaries/releases/download/jdk8u352-b08/OpenJDK8U-jre_x64_linux_hotspot_8u352b08.tar.gz" \
#    && mkdir ${JAVA_HOME} \
#    && tar -xf jre.tar.gz -C $JAVA_HOME --strip-components 1 --no-same-owner \
#    && rm jre.tar.gz

ENTRYPOINT ["java", "-jar", "downwardapi_test-1.0-SNAPSHOT-jar-with-dependencies.jar"]