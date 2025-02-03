package ru.sidorov.telros.models.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
@AllArgsConstructor
@Schema(description = "Роль пользователя")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(description = "Уникальный идентификатор роли", example = "1")
    private Integer id;

    @Column(name = "name", nullable = false)
    @Schema(description = "Наименование роли", example = "ROLE_USER")
    private String name;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
