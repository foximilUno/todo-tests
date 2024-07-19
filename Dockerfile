FROM gradle:7.6.4-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
ENV SERVICE_ENDPOINT "http://localhost:8080"
CMD gradle clean test --info