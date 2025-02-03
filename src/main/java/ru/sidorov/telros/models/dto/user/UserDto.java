package ru.sidorov.telros.models.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Пользователь для отображения")
public class UserDto {
    @Schema(description = "Уникальный идентификатор пользователя", example = "1")
    private Integer id;
    @Schema(description = "Почта пользователя", example = "vitaliy@yandex.ru")
    private String email;
    @Schema(description = "Имя пользователя", example = "Виталий")
    private String firstname;
    @Schema(description = "Фамилия пользователя", example = "Сидоров")
    private String lastname;
    @Schema(description = "Отчество пользователя", example = "Анатольевич")
    private String surname;
    @Schema(description = "Дата рождения пользователя", example = "1996-08-22")
    private LocalDate birthday;
    @Schema(description = "Номер телефона пользователя", example = "+79999999999")
    private String contactPhone;
    @Schema(description = "Аватар пользователя")
    private String pictureUrl;
}
