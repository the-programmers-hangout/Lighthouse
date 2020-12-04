FROM gradle:6.5.1-jdk14 AS build
COPY --chown=gradle:gradle . /lighthouse
WORKDIR /lighthouse
RUN gradle shadowJar --no-daemon

FROM openjdk:11.0.8-jre-slim
RUN mkdir /config/
COPY --from=build /lighthouse/build/libs/*.jar /

ENTRYPOINT ["java", "-jar", "/Lighthouse.jar"]