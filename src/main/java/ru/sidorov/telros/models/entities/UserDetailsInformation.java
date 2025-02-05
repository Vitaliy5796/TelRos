package ru.sidorov.telros.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users_details_information")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Детальная информация пользователя")
public class UserDetailsInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Уникальный идентификатор детальной информации пользователя", example = "1")
    private Integer id;

    @Column(name = "firstname")
    @Schema(description = "Имя пользователя", example = "Виталий")
    private String firstname;

    @Column(name = "lastname")
    @Schema(description = "Фамилия пользователя", example = "Сидоров")
    private String lastname;

    @Column(name = "surname")
    @Schema(description = "Отчество пользователя", example = "Анатольевич")
    private String surname;

    @Schema(description = "Дата рождения пользователя", example = "1996-08-22")
    @Column(name = "birth_day")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Schema(description = "Аватар пользователя")
    @Column(name = "picture_url")
    private String pictureUrl;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonIgnore
    @Schema(description = "Владелец детальной информации")
    private User user;
}
