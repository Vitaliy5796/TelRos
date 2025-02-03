package ru.sidorov.telros.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Пользователь")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(description = "Уникальный идентификатор пользователя", example = "1")
    private Integer id;

    @Column(name = "password", nullable = false)
    @Schema(description = "Пароль пользователя", example = "password")
    private String password;

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

    @Column(name = "email", unique = true, nullable = false)
    @Schema(description = "Почта пользователя", example = "vitaliy@yandex.ru")
    private String email;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "picture_url")
    private String pictureUrl;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "role_id", nullable = false)
    @Schema(description = "Роль пользователя")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getPassword() {return password;}

    @Override
    public String getUsername() {return email;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
