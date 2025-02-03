package ru.sidorov.telros.models.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Пользователь для сохранения")
public class UserSaveDto {

    @Schema(description = "Уникальный идентификатор пользователя (Только для изменения)", example = "1")
    private Integer id;

    @Email(message = "Email должен быть в корректном формате")
    @NotBlank(message = "Email не может быть пустым")
    @Schema(description = "Почта пользователя", example = "vitaliy@yandex.ru")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    @Schema(description = "Пароль пользователя", example = "vitaliy")
    private String password;

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 3, max = 50, message = "Имя пользователя должно быть от 3 до 50 символов")
    @Schema(description = "Имя пользователя", example = "Виталий")
    private String firstname;

    @NotBlank(message = "Фамилия пользователя не может быть пустым")
    @Size(min = 3, max = 50, message = "Фамилия пользователя должно быть от 3 до 50 символов")
    @Schema(description = "Фамилия пользователя", example = "Сидоров")
    private String lastname;

    @Size(min = 3, max = 50, message = "Отчество пользователя должно быть от 3 до 50 символов")
    @Schema(description = "Отчество пользователя", example = "Анатольевич")
    private String surname;

    @NotNull(message = "Дата рождения пользователя не может быть пустой")
    @Past(message = "Дата рождения должна быть в прошлом")
    @Schema(description = "Дата рождения пользователя", example = "1996-08-22")
    private LocalDate birthday;

    @NotBlank(message = "Номер телефона пользователя не может быть пустым")
    @Pattern(regexp = "\\+7\\d{10}", message = "Номер телефона должен быть в формате +7XXXXXXXXXX")
    @Schema(description = "Номер телефона пользователя", example = "+79999999999")
    private String contactPhone;

    @Schema(description = "Аватар пользователя")
    private String pictureUrl;
}