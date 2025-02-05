package ru.sidorov.telros.models.exception;

public class NotFoundUserDetailsInformationException extends NotFoundException {

    public static final String ERROR_MSG = "Детальная информация пользователя не найдена";

    public static final String ERROR_MSG_USER_WITH_ID = "Детальная информация пользователя c id = %d не найдена";

    public NotFoundUserDetailsInformationException() {
        super(ERROR_MSG);
    }

    public NotFoundUserDetailsInformationException(Integer userId) {
        super(String.format(ERROR_MSG_USER_WITH_ID, userId));
    }
}
