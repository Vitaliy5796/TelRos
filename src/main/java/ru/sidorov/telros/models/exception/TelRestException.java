package ru.sidorov.telros.models.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class TelRestException extends IllegalArgumentException {
    private HttpStatus httpStatus;

    public TelRestException(HttpStatus httpStatus, String msg) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
