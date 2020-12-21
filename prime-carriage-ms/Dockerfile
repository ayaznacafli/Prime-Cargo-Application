FROM alpine:3.11.2
RUN apk add --no-cache openjdk11
COPY build/libs/prime-carriage-ms-0.1.2fe9f8a.jar /app/
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar", "/app/prime-carriage-ms-0.1.2fe9f8a.jar"]
