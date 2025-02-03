package ru.sidorov.telros.models.exception;

public class UserWithEmailAlreadyExistsException extends UserAlreadyExistsException {

    public static final String ERROR_MSG = "Пользователь с таким email уже существует!";

    public static final String ERROR_MSG_USER_WITH_EMAIL = "Пользователь c email: %s уже существует";

    public UserWithEmailAlreadyExistsException() {
        super(ERROR_MSG);
    }

    public UserWithEmailAlreadyExistsException(String email) {
        super(String.format(ERROR_MSG_USER_WITH_EMAIL, email));
    }
}
