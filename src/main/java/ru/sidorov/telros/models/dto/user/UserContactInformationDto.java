package ru.sidorov.telros.models.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sidorov.telros.models.dto.userDetailsInformation.UserDetailsInformationDto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Контактная информация пользователя")
@JsonInclude(NON_NULL)
public class UserContactInformationDto {
    @Schema(description = "Уникальный идентификатор пользователя", example = "1")
    private Integer id;
    @Schema(description = "Почта пользователя", example = "vitaliy@yandex.ru")
    private String email;
    @Schema(description = "Номер телефона пользователя", example = "+79999999999")
    private String contactPhone;
    @Schema(description = "Детальная информация пользователя")
    private UserDetailsInformationDto userDetailsInformation;
}
