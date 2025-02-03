package ru.sidorov.telros.models.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends TelRestException {

    public static final String DEFAULT_ERROR_MSG = "Объект не найден";

    public NotFoundException() {
        this(DEFAULT_ERROR_MSG);
    }

    public NotFoundException(String msg) {
        super(HttpStatus.NOT_FOUND, msg);
    }
}
