package ru.sidorov.telros.models.dto.common;

import org.springframework.http.HttpStatus;
import ru.sidorov.telros.models.enums.Result;

public class TelResponseOkEntity<T> extends TelResponseEntity<T> {

    public TelResponseOkEntity(HttpStatus status, T operationResultOnject) {
        super(status, Result.OK, operationResultOnject, null);
    }

    public TelResponseOkEntity(T operationResultOnject) {
        this(HttpStatus.OK, operationResultOnject);
    }

    public TelResponseOkEntity() {
        this(HttpStatus.OK, null);
    }
}
