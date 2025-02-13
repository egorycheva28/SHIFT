package ru.cft.template.core.exceptionHandler;

import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.cft.template.core.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = SessionNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleSessionNotFoundException(SessionNotFoundException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", "Login Error");
        errorBody.put("message", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", "Login Error");
        errorBody.put("message", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = WalletNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleWalletNotFoundException(WalletNotFoundException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", "Login Error");
        errorBody.put("message", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TransferNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleTransferNotFoundException(TransferNotFoundException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", "Login Error");
        errorBody.put("message", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MissingRequestHeaderException.class)
    public ResponseEntity<Map<String, Object>> handleAuthorizationException(MissingRequestHeaderException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", "Authorization Error");
        errorBody.put("message", "Вы не авторизованы.");

        return new ResponseEntity<>(errorBody, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, Object>> handleParametersException(MissingServletRequestParameterException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", "Authorization Error");
        errorBody.put("message", "Параметры пагинации - это обязательные поля.");

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleParametersException(MethodArgumentTypeMismatchException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", "Authorization Error");
        errorBody.put("message", "Неправильное значение фильтра.");

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AccessRightsException.class)
    public ResponseEntity<Map<String, Object>> handleAccessRightsException(AccessRightsException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", "Login Error");
        errorBody.put("message", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = BalanceException.class)
    public ResponseEntity<Map<String, Object>> handleBalanceException(BalanceException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", "Login Error");
        errorBody.put("message", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = LoginException.class)
    public ResponseEntity<Map<String, Object>> handleLoginException(LoginException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", "Login Error");
        errorBody.put("message", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleCreateUserException(ValidationException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", "Validation Error");
        errorBody.put("message", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PaginationException.class)
    public ResponseEntity<Map<String, Object>> handlePaginationException(PaginationException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("error", "Pagination Error");
        errorBody.put("message", ex.getMessage());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

}
