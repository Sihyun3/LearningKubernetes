FROM openjdk
RUN microdnf install findutils
RUN mkdir /project
WORKDIR /project
COPY ./ .
RUN ./gradlew build
ENTRYPOINT java -jar /project/build/libs/*.jar
