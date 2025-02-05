package ru.sidorov.telros.services.abstracts;

import org.springframework.web.multipart.MultipartFile;
import ru.sidorov.telros.models.entities.UserDetailsInformation;

import java.io.IOException;

public interface UserService {

    // ==================== Работа с аватаром ====================

    /**
     * Добавление аватара пользователю.
     *
     * @param file {@link MultipartFile} аватар для загрузки
     * @param userDetailsInformation {@link UserDetailsInformation} пользователь, которому добавляется аватар
     * @return {@link String} аватар пользователя
     * @throws IOException если ошибка при загрузке файла
     */
    String addUserAvatar(MultipartFile file, UserDetailsInformation userDetailsInformation) throws IOException;

    /**
     * Удаление аватара пользователя.
     *
     * @param userDetailsInformation {@link UserDetailsInformation} пользователь, у которого удаляется аватар
     */
    void delUserAvatar(UserDetailsInformation userDetailsInformation);

    /**
     * Получение пути к аватару пользователя.
     *
     * @param userDetailsInformation {@link UserDetailsInformation} пользователь, чей аватар запрашивается
     * @return {@link String} путь к файлу аватара
     */
    String getUserAvatar(UserDetailsInformation userDetailsInformation);
}
