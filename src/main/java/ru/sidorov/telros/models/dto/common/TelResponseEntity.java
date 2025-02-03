package ru.sidorov.telros.models.dto.common;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import ru.sidorov.telros.models.enums.Result;

public class TelResponseEntity<T> extends ResponseEntity<OperationResult<T>> {

    public TelResponseEntity(HttpStatusCode status, Result operationResult, T operationResultObject, String operationInfo) {
        super(new OperationResult<>(operationResult, operationResultObject, operationInfo), status);
    }
}
