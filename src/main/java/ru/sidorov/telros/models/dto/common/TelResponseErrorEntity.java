package ru.sidorov.telros.models.dto.common;

import org.springframework.http.HttpStatus;
import ru.sidorov.telros.models.enums.Result;
import ru.sidorov.telros.models.exception.TelRestException;

public class TelResponseErrorEntity<T> extends TelResponseEntity<T> {

    public TelResponseErrorEntity(HttpStatus httpStatus, String operationInfo) {
        super(httpStatus, Result.ERROR, null, operationInfo);
    }

    public TelResponseErrorEntity(String operationInfo) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, operationInfo);
    }

    public TelResponseErrorEntity(TelRestException e) {
        this(e.getHttpStatus(), e.getMessage());
    }

    public TelResponseErrorEntity(Exception e) {
        this(e.getMessage());
    }
}
