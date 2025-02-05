package ru.sidorov.telros.models.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sidorov.telros.models.enums.Result;

@Schema(description = "Сущность, описывающая результат выполнения операции")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationResult<T>{

    @Schema(description = "Результат выполнения операции")
    private Result operationResult;
    @Schema(description = "Объект, возвращаемый операцией")
    private T object;
    @Schema(description = "Дополнительная информация к результату выполнения (как правило описание ошибки)")
    private String operationInfo;
}