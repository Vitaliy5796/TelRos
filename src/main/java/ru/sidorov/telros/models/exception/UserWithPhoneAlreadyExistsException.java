package ru.sidorov.telros.models.exception;

public class UserWithPhoneAlreadyExistsException extends UserAlreadyExistsException {

    public static final String ERROR_MSG = "Пользователь с таким телефоном уже существует!";

    public static final String ERROR_MSG_USER_WITH_PHONE = "Пользователь c телефоном: %s уже существует";

    public UserWithPhoneAlreadyExistsException() {
        super(ERROR_MSG);
    }

    public UserWithPhoneAlreadyExistsException(String phone) {
        super(String.format(ERROR_MSG_USER_WITH_PHONE, phone));
    }
}
