package ru.sidorov.telros.models.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sidorov.telros.models.enums.Result;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationResult<T>{

    private Result operationResult;
    private T object;
    private String operationInfo;
}