package ru.cft.template.core.exception;

public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException(String message) {

        super(message);
    }
}
