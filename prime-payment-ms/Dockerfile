FROM alpine:3.11.2
RUN apk add --no-cache openjdk11
COPY build/libs/prime-payment-ms-0.1.5c91992.jar /app/
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar", "/app/prime-payment-ms-0.1.5c91992.jar"]
