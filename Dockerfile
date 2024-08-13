# Usar uma imagem base do Maven para compilar o projeto
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Usar uma imagem base do OpenJDK para rodar a aplicação
FROM eclipse-temurin:21
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar

# Variáveis de ambiente para o aplicativo Java
ENV JAVA_OPTS=""
ENV SPRING_APPLICATION_NAME=picpay-desafio-backend
ENV SPRING_DATASOURCE_URL=jdbc:h2:file:./data/picpay
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=sa
ENV SPRING_DATASOURCE_PASSWORD=password
ENV SPRING_H2_CONSOLE_ENABLED=true
ENV SPRING_H2_CONSOLE_PATH=/h2-console
ENV SPRING_SQL_INIT_MODE=always
ENV SPRING_KAFKA_BOOTSTRAP_SERVERS=localhost:9094
ENV SPRING_KAFKA_PRODUCER_VALUE_SERIALIZER=org.springframework.kafka.support.serializer.JsonSerializer
ENV SPRING_KAFKA_CONSUMER_VALUE_DESERIALIZER=org.springframework.kafka.support.serializer.JsonDeserializer
ENV SPRING_KAFKA_CONSUMER_PROPERTIES_SPRING_JSON_TRUSTED_PACKAGES=*

# Ponto de entrada para nosso aplicativo Spring Boot
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar" ]