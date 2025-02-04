package ru.sidorov.telros.models.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends TelRestException {

    public static final String DEFAULT_ERROR_MSG = "Username уже существует";

    public UserAlreadyExistsException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

    public UserAlreadyExistsException() {
        this(DEFAULT_ERROR_MSG);
    }
}
