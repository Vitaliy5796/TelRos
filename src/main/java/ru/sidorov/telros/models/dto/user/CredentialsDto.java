package ru.sidorov.telros.models.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Логин и пароль для авторизации")
public class CredentialsDto {

    @Email
    @Schema(description = "Логин (email) для входа",example = "vitaliy@yandex.ru")
    private String login;
    @Schema(description = "Пароль для входа", example = "vitaliy")
    private String password;
}
