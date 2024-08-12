package br.com.mizaeldouglas.api.service.authorization;

import br.com.mizaeldouglas.api.domain.transaction.Transaction;
import br.com.mizaeldouglas.api.exceptions.authorization.UnauthorizedTransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
public class AuthorizerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizerService.class);
    private RestClient restClient;

    public AuthorizerService(RestClient.Builder builder) {
        this.restClient = builder.baseUrl(
                "https://run.mocky.io/v3/9d53306b-5155-4dcf-8ecd-cddfc1fd25d1").build();
    }

    public void authorize(Transaction transaction) {
        LOGGER.info("authorizing transaction {}...", transaction);

        var response = restClient.get().retrieve().toEntity(Authorization.class);
        if (response.getStatusCode().isError() || !response.getBody().isAuthorized())
            throw new UnauthorizedTransactionException("Unauthorized!");
    }
}

record Authorization(String message) {
    public boolean isAuthorized() {
        return message.equals("Autorizado");
    }
}
