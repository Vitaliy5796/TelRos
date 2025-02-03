package ru.sidorov.telros.models.exception;

import org.springframework.http.HttpStatus;

public class NullIdException extends TelRestException {

    public static final String DEFAULT_ERROR_MSG = "Поле id не может быть null";

    public NullIdException() {
        this(DEFAULT_ERROR_MSG);
    }

    public NullIdException(String msg) {
        super(HttpStatus.NOT_FOUND, msg);
    }
}
