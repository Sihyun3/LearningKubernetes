FROM openjdk
RUN microdnf install findutils
RUN mkdir /project
WORKDIR /project
COPY ./ .
RUN ./gradlew build
WORKDIR build/libs
RUN chmod +x *.jar
ENTRYPOINT	 ["java","-jar","*.jar"]
