package ru.cft.template.core.exception;

public class WalletNotFoundException extends RuntimeException {

    public WalletNotFoundException(String message) {

        super(message);
    }
}
