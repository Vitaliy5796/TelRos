package ru.sidorov.telros.repositories;

import org.springframework.stereotype.Repository;
import ru.sidorov.telros.models.entities.User;

import java.util.Optional;

/**
 * Репозиторий пользователя
 */
@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByContactPhone(String phone);
}
