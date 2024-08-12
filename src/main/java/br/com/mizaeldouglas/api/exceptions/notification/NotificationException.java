package br.com.mizaeldouglas.api.exceptions.notification;

public class NotificationException extends RuntimeException {
    public NotificationException(String message) {
        super(message);
    }
}