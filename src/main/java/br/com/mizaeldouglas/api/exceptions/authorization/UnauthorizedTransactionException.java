package br.com.mizaeldouglas.api.exceptions.authorization;

public class UnauthorizedTransactionException extends RuntimeException {
    public UnauthorizedTransactionException(String message) {
        super(message);
    }
}