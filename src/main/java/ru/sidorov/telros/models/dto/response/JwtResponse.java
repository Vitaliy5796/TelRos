package ru.sidorov.telros.models.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "Ответ для получения токена")
@Data
@AllArgsConstructor
public class JwtResponse {

    @Schema(description = "Логин пользователя")
    private String login;
    @Schema(description = "Сгенерированный jwt токен")
    private String token;

}
