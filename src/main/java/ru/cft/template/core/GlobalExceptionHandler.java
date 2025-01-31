package ru.cft.template.core;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.cft.template.core.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = SessionNotFoundException.class)
    public ResponseEntity<Object> handleSessionNotFoundException(SessionNotFoundException ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = WalletNotFoundException.class)
    public ResponseEntity<Object> handleWalletNotFoundException(WalletNotFoundException ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TransferNotFoundException.class)
    public ResponseEntity<Object> handleTransferNotFoundException(TransferNotFoundException ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseEntity<Object> handleAuthorizationException(AuthorizationException ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = AccessRightsException.class)
    public ResponseEntity<Object> handleAccessRightsException(AccessRightsException ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = BalanceException.class)
    public ResponseEntity<Object> handleBalanceException(BalanceException ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.FORBIDDEN);
    }
}
