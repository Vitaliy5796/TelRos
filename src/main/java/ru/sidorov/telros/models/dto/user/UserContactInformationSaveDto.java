package ru.sidorov.telros.models.dto.user;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Контактная информация пользователя для сохранения")
public class UserContactInformationSaveDto {

    @Email(message = "Email должен быть в корректном формате")
    @NotBlank(message = "Email не может быть пустым")
    @Schema(description = "Почта пользователя", example = "vitaliy@yandex.ru")
    private String email;

    @NotBlank(message = "Номер телефона пользователя не может быть пустым")
    @Pattern(regexp = "\\+7\\d{10}", message = "Номер телефона должен быть в формате +7XXXXXXXXXX")
    @Schema(description = "Номер телефона пользователя", example = "+79999999999")
    private String contactPhone;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    @Schema(description = "Пароль пользователя", example = "vitaliy")
    private String password;
}
