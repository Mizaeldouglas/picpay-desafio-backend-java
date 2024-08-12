package br.com.mizaeldouglas.api.domain.authorization;

public record Authorization(
        String message
) {
    public boolean isAuthorized() {
        return message.equals("Autorizado");
    }
}
