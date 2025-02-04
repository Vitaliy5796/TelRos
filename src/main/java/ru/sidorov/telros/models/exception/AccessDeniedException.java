package ru.sidorov.telros.models.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends TelRestException {

    public static final String DEFAULT_ERROR_MSG = "Вы не можете удалить этот аккаунт";

    public AccessDeniedException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

    public AccessDeniedException() {
        this(DEFAULT_ERROR_MSG);
    }
}
