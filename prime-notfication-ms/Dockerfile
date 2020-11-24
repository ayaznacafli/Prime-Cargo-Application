FROM alpine:3.11.2
RUN apk add --no-cache openjdk11
COPY build/libs/prime-notfication-ms-0.1.366cf57.jar /app/
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar", "/app/prime-notfication-ms-0.1.366cf57.jar"]
