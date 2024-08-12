package br.com.mizaeldouglas.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.kafka.config.TopicBuilder;

@EnableJdbcAuditing
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "PicPay Desafio Backend", version = "1.0", description = "API do PicPay Desafio Backend"))
public class PicpayDesafioBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PicpayDesafioBackendApplication.class, args);

    }

    @Bean
    NewTopic notificationTopic() {
        return TopicBuilder.name("transaction-notification")
                .build();
    }

}
