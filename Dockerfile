FROM openjdk
RUN microdnf install findutils
RUN mkdir /project
WORKDIR /project
COPY ./ .
RUN chmod +x gradlew
RUN ./gradlew build
ENTRYPOINT java -jar /project/build/libs/*.jar
