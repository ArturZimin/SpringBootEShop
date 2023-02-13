FROM openjdk:11
WORKDIR /app
EXPOSE 8080
ADD build/libs/eshop-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

#FROM openjdk:11 as builder
#WORKDIR /app
#COPY . .
#RUN microdnf install findutils
#RUN ./gradlew build
#COPY build/libs/*.jar app.jar
#
#FROM openjdk:17
#COPY --from=builder /build/libs/eshop-0.0.1-SNAPSHOT.jar .
#ENTRYPOINT ["java", "-jar", "/eshop-0.0.1-SNAPSHOT.jar"]
#EXPOSE 8080

