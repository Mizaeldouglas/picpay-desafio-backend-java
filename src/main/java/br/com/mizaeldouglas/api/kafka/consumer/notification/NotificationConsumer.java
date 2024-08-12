package br.com.mizaeldouglas.api.kafka.consumer.notification;

import br.com.mizaeldouglas.api.domain.transaction.Transaction;
import br.com.mizaeldouglas.api.exceptions.notification.NotificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class NotificationConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);
    private final RestClient restClient;

    public NotificationConsumer(RestClient.Builder builder) {
        this.restClient = builder.baseUrl(
                        "https://run.mocky.io/v3/080eb1e9-6e1c-4e88-92fb-db166bff77cc")
                .build();
    }

    @KafkaListener(topics = "transaction-notification", groupId = "picpay-desafio-backend")
    public void receiveNotification(Transaction transaction) {
        try {
            LOGGER.info("notifying transaction {}...", transaction);
            var response = restClient.post()
                    .uri("/notify")
                    .body(transaction)
                    .retrieve()
                    .toEntity(String.class);

            if (response == null || response.getStatusCode().isError()) {
                throw new NotificationException("Error notifying transaction " + transaction);
            }

            LOGGER.info("Notification sent successfully for transaction {}", transaction);
        } catch (RestClientException e) {
            LOGGER.error("Error notifying transaction {}: {}", transaction, e.getMessage());
            throw new NotificationException("Error notifying transaction " + transaction);
        }
    }
}

//https://run.mocky.io/v3/9d53306b-5155-4dcf-8ecd-cddfc1fd25d1