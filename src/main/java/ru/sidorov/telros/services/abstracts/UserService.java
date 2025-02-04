package ru.sidorov.telros.services.abstracts;

import org.springframework.web.multipart.MultipartFile;
import ru.sidorov.telros.models.dto.user.UserDto;
import ru.sidorov.telros.models.dto.user.UserSaveDto;
import ru.sidorov.telros.models.entities.User;
import ru.sidorov.telros.models.exception.AccessDeniedException;
import ru.sidorov.telros.models.exception.NotFoundUserException;

import java.io.IOException;

public interface UserService {


    // ==================== Работа с пользователем ====================

    /**
     * Сохранение нового пользователя в БД.
     *
     * @param userDto {@link UserSaveDto} сохраняемый пользователь
     * @return {@link UserDto} сохраненный пользователь
     * @throws IllegalArgumentException если данные некорректны
     */
    UserDto save(UserSaveDto userDto);

    /**
     * Получение пользователя по ID.
     *
     * @param id {@link Integer} уникальный идентификатор
     * @return {@link User} полученный пользователь
     * @throws NotFoundUserException если пользователь не найден
     */
    User getUser(Integer id);

    /**
     * Обновление данных пользователя.
     *
     * @param userDto {@link UserSaveDto} данные для обновления
     * @return {@link UserDto} обновленный пользователь
     * @throws NotFoundUserException если пользователь не найден
     */
    UserDto updateUser(UserSaveDto userDto);

    /**
     * Удаление пользователя.
     *
     * @param id   {@link Integer} ID удаляемого пользователя
     * @param user {@link User} авторизованный пользователь (админ или сам себя удаляет)
     * @throws NotFoundUserException если пользователь не найден
     * @throws AccessDeniedException если нет прав на удаление
     */
    void delUser(Integer id, User user);

    // ==================== Работа с аватаром ====================

    /**
     * Добавление аватара пользователю.
     *
     * @param file {@link MultipartFile} аватар для загрузки
     * @param user {@link User} пользователь, которому добавляется аватар
     * @return {@link UserDto} пользователь с обновленным аватаром
     * @throws IOException если ошибка при загрузке файла
     */
    UserDto addUserAvatar(MultipartFile file, User user) throws IOException;

    /**
     * Удаление аватара пользователя.
     *
     * @param user {@link User} пользователь, у которого удаляется аватар
     */
    void delUserAvatar(User user);

    /**
     * Получение пути к аватару пользователя.
     *
     * @param user {@link User} пользователь, чей аватар запрашивается
     * @return {@link String} путь к файлу аватара
     */
    String getUserAvatar(User user);
}
