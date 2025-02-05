package ru.sidorov.telros.services.abstracts;

import ru.sidorov.telros.models.dto.userDetailsInformation.UserDetailsInformationDto;
import ru.sidorov.telros.models.dto.userDetailsInformation.UserDetailsInformationUpdateDto;
import ru.sidorov.telros.models.exception.AccessDeniedException;
import ru.sidorov.telros.models.exception.NotFoundUserDetailsInformationException;

public interface UserDetailsInformationService {

    /**
     * Сохранение детальной информаци пользователя в БД.
     *
     * @param userDto {@link UserDetailsInformationDto} сохраняемая детальная информаци пользователя
     * @param userId {@link Integer} ID пользователя
     * @return {@link UserDetailsInformationDto} сохраненный детальная информаци пользователя
     * @throws IllegalArgumentException если данные некорректны
     */
    UserDetailsInformationDto save(UserDetailsInformationDto userDto, Integer userId);

    /**
     * Получение детальная информаци пользователя по ID.
     *
     * @param detailsId {@link Integer} уникальный идентификатор
     * @param userId {@link Integer} ID пользователя
     * @return {@link UserDetailsInformationDto} полученная детальная информаци пользователя
     * @throws NotFoundUserDetailsInformationException если детальная информация не найдена
     */
    UserDetailsInformationDto getUserDetailsInformation(Integer detailsId, Integer userId);

    /**
     * Обновление данных детальной информаци пользователя.
     *
     * @param userDto {@link UserDetailsInformationUpdateDto} данные для обновления
     * @param userId {@link Integer} ID пользователя
     * @return {@link UserDetailsInformationDto} обновленная детальная информаци пользователя
     * @throws NotFoundUserDetailsInformationException если детальная информация не найдена
     */
    UserDetailsInformationDto updateUserDetailsInformation(UserDetailsInformationUpdateDto userDto, Integer userId);

    /**
     * Удаление детальная информаци пользователя.
     *
     * @param detailsId {@link Integer} ID удаляемой детальной информаци пользователя
     * @param userId {@link Integer} ID пользователя
     * @throws NotFoundUserDetailsInformationException если детальная информаци пользователя не найдена
     * @throws AccessDeniedException если нет прав на удаление
     */
    void delUserDetailsInformation(Integer detailsId, Integer userId);
}
