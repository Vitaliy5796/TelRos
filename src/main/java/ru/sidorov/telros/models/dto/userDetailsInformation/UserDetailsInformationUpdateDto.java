package ru.sidorov.telros.models.dto.userDetailsInformation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sidorov.telros.models.dto.user.UserDto;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Детальная информация пользователя")
public class UserDetailsInformationUpdateDto {

    @Schema(description = "Уникальный идентификатор детальной информации пользователя", example = "1")
    private Integer id;

    @Size(min = 3, max = 50, message = "Имя пользователя должно быть от 3 до 50 символов")
    @Schema(description = "Имя пользователя", example = "Виталий")
    private String firstname;

    @Size(min = 3, max = 50, message = "Фамилия пользователя должно быть от 3 до 50 символов")
    @Schema(description = "Фамилия пользователя", example = "Сидоров")
    private String lastname;

    @Size(min = 3, max = 50, message = "Отчество пользователя должно быть от 3 до 50 символов")
    @Schema(description = "Отчество пользователя", example = "Анатольевич")
    private String surname;

    @Past(message = "Дата рождения должна быть в прошлом")
    @Schema(description = "Дата рождения пользователя", example = "1996-08-22")
    private LocalDate birthday;

    @Schema(description = "Владелец детальной информации")
    private UserDto user;
}
