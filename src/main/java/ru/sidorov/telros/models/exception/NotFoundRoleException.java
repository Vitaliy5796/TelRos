package ru.sidorov.telros.models.exception;

public class NotFoundRoleException extends NotFoundException {

    public static final String ERROR_MSG = "Роль не найдена";

    public static final String ERROR_MSG_ROLE_WITH_ID = "Роль c id = %d не найдена";

    public NotFoundRoleException() {
        super(ERROR_MSG);
    }

    public NotFoundRoleException(Integer roleId) {
        super(String.format(ERROR_MSG_ROLE_WITH_ID, roleId));
    }
}
