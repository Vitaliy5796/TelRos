package ru.sidorov.telros.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
    public TelResponseEntity<UserDto> updateUser(@RequestBody UserSaveDto userDto) {
        log.info("[updateUser] Starting");
        TelResponseEntity<UserDto> responseEntity;

        try {
            responseEntity = new TelResponseOkEntity<>(userService.updateByDto(userDto));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[updateUser] Starting");
        return responseEntity;
    }

    @Operation(summary = "Добавление аватара для пользователя", description = "Возвращает dto пользователя")
    @RequestMapping(value = "/addAvatar", produces = "application/json", method = RequestMethod.POST)
    public TelResponseEntity<UserDto> addUserAvatar(@RequestParam("file") MultipartFile file,
                                                    HttpServletRequest request) {
        log.info("[addUserAvatar] Starting");
        TelResponseEntity<UserDto> responseEntity;
        try {
            User user = jwtUtils.getUserFromToken(jwtUtils.getTokenFromRequest(request));
            responseEntity = new TelResponseOkEntity<>(userService.addUserAvatar(file, user));
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[addUserAvatar] Done");
        return responseEntity;
    }

    @Operation(summary = "Получение аватара пользователя", description = "Возвращает аватар пользователя")
    @RequestMapping(value = "/avatar", produces = "application/json", method = RequestMethod.GET)
    public TelResponseEntity<String> getUserAvatar(HttpServletRequest request) {
        log.info("[getUserAvatar] Starting");
        TelResponseEntity<String> responseEntity;

        try {
            User user = jwtUtils.getUserFromToken(jwtUtils.getTokenFromRequest(request));
            responseEntity = new TelResponseOkEntity<>(userService.getUserAvatar(user));
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[getUserAvatar] Done");
        return responseEntity;
    }

    @Operation(summary = "Удаление аватара пользователя", description = "Возвращает строку об успешном удалении аватара")
    @RequestMapping(value = "/delAvatar", produces = "application/json", method = RequestMethod.DELETE)
    public TelResponseEntity<String> deleteUserAvatar(HttpServletRequest request) {
        log.info("[deleteUserAvatar] Starting");
        TelResponseEntity<String> responseEntity;

        try {
            userService.delUserAvatar(jwtUtils.getUserFromToken(jwtUtils.getTokenFromRequest(request)));
            responseEntity = new TelResponseOkEntity<>("Аватар пользователя удален");
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[deleteUserAvatar] Done");
        return responseEntity;
    }
}
