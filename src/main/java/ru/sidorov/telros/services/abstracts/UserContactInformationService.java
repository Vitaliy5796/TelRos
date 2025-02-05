package ru.sidorov.telros.services.abstracts;

import ru.sidorov.telros.models.dto.user.UserContactInformationDto;
import ru.sidorov.telros.models.dto.user.UserContactInformationSaveDto;
import ru.sidorov.telros.models.dto.user.UserContactInformationUpdateDto;
import ru.sidorov.telros.models.exception.AccessDeniedException;
import ru.sidorov.telros.models.exception.NotFoundUserException;

/**
 * Сервис для работы с пользователем
 */
public interface UserContactInformationService {

    /**
     * Сохранение нового пользователя в БД.
     *
     * @param userDto {@link UserContactInformationSaveDto} сохраняемый пользователь
     * @return {@link UserContactInformationDto} сохраненный пользователь
     * @throws IllegalArgumentException если данные некорректны
     */
    UserContactInformationDto save(UserContactInformationSaveDto userDto);

    /**
     * Получение пользователя по ID.
     *
     * @param id {@link Integer} уникальный идентификатор
     * @return {@link UserContactInformationDto} полученный пользователь
     * @throws NotFoundUserException если пользователь не найден
     */
    UserContactInformationDto getUser(Integer id);

    /**
     * Обновление данных пользователя.
     *
     * @param userDto {@link UserContactInformationUpdateDto} данные для обновления
     * @return {@link UserContactInformationDto} обновленный пользователь
     * @throws NotFoundUserException если пользователь не найден
     */
    UserContactInformationDto updateUser(UserContactInformationUpdateDto userDto);

    /**
     * Удаление пользователя.
     *
     * @param id   {@link Integer} ID удаляемого пользователя
     * @throws NotFoundUserException если пользователь не найден
     * @throws AccessDeniedException если нет прав на удаление
     */
    void delUser(Integer id);
}
