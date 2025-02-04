package ru.sidorov.telros.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sidorov.telros.config.jwt.JwtUtils;
import ru.sidorov.telros.models.dto.common.TelResponseEntity;
import ru.sidorov.telros.models.dto.common.TelResponseErrorEntity;
import ru.sidorov.telros.models.dto.common.TelResponseOkEntity;
import ru.sidorov.telros.models.dto.user.UserDto;
import ru.sidorov.telros.models.dto.user.UserSaveDto;
import ru.sidorov.telros.models.entities.User;
import ru.sidorov.telros.services.abstracts.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Пользователь", description = "API для работы с пользователем")
public class UserRestController {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Operation(summary = "Обновление информации о пользователе", description = "Возвращает обновленную информацию о пользователе")
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.PATCH)
    public TelResponseEntity<UserDto> updateUser(@Valid @RequestBody UserSaveDto userDto) {
        log.info("[updateUser] Starting");
        TelResponseEntity<UserDto> responseEntity;

        try {
            responseEntity = new TelResponseOkEntity<>(userService.updateUser(userDto));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[updateUser] Done");
        return responseEntity;
    }

    @Operation(summary = "Удаление своего аккаунта пользователя или удаление пользователя администратором", description = "Возвращает строку с информации об успешности операции")
    @RequestMapping(value = "/{id}/delUser", produces = "application/json", method = RequestMethod.DELETE)
    public TelResponseEntity<String> delUser(@PathVariable("id") Integer id,
                                             HttpServletRequest request) {
        log.info("[delUser] Starting");
        TelResponseEntity<String> responseEntity;

        try {
            userService.delUser(id, jwtUtils.getUserFromToken(jwtUtils.getTokenFromRequest(request)));
            responseEntity = new TelResponseOkEntity<>(String.format("Пользователь с id: %d успешно удален", id));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[delUser] Done");
        return responseEntity;
    }
}
