FROM maven:3.8.4-openjdk-11-slim@sha256:4576fdba48cd1444f02f52a3d811aab177b283c1248869d7e546d89c92c83d59 AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests

FROM eclipse-temurin:11.0.14_9-jre-alpine@sha256:2d9fe6caa720dc96202fca6f65e0d00d2db7a58bd4cc34251c52b9172b467b79
RUN apk add dumb-init
RUN mkdir /app
RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=build /project/target/codecoin-0.0.1-SNAPSHOT.jar /app/codecoin.jar
WORKDIR /app
RUN chown -R javauser:javauser /app
USER javauser
CMD "dumb-init" "java" "-jar" "codecoin.jar"
EXPOSE 8080
