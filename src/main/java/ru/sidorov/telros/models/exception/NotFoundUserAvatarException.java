package ru.sidorov.telros.models.exception;

public class NotFoundUserAvatarException extends NotFoundException {

    public static final String ERROR_MSG = "Аватар пользователя не найден";

    public static final String ERROR_MSG_USER_WITH_ID = "Аватар пользователя c id = %d не найден";

    public NotFoundUserAvatarException() {
        super(ERROR_MSG);
    }

    public NotFoundUserAvatarException(Integer userId) {
        super(String.format(ERROR_MSG_USER_WITH_ID, userId));
    }
}
