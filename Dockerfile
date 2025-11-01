FROM eclipse-temurin:21-jdk-alpine-3.22

RUN apk add --no-cache tzdata
RUN ln -snf /usr/share/zoneinfo/Asia/Seoul /etc/localtime

COPY build/libs/*.jar app.jar

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -Dspring.profiles.active=${PROFILE} -jar /app.jar"]
