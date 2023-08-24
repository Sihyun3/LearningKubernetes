FROM openjdk
RUN microdnf install findutils
RUN mkdir /project
WORKDIR /project
COPY ./ .
RUN ./gradlew build
WORKDIR build/libs
ENTRYPOINT java -jar *.jar
