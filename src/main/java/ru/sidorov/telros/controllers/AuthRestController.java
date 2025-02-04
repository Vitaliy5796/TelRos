package ru.sidorov.telros.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sidorov.telros.models.dto.common.TelResponseEntity;
import ru.sidorov.telros.models.dto.common.TelResponseErrorEntity;
import ru.sidorov.telros.models.dto.common.TelResponseOkEntity;
import ru.sidorov.telros.models.dto.response.JwtResponse;
import ru.sidorov.telros.models.dto.user.CredentialsDto;
import ru.sidorov.telros.models.dto.user.UserDto;
import ru.sidorov.telros.models.dto.user.UserSaveDto;
import ru.sidorov.telros.services.abstracts.UserService;
import ru.sidorov.telros.services.implementation.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Авторизация и регистрация", description = "API для авторизации и регистрации")
public class AuthRestController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация нового пользователя", description = "Возвращает зарегистрировавшегося пользователя")
    @RequestMapping(value = "/registration", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public TelResponseEntity<UserDto> registration(@Valid @RequestBody UserSaveDto userDto) {
        log.info("[registration] Starting");
        TelResponseEntity<UserDto> responseEntity;

        try {
            responseEntity = new TelResponseOkEntity<>(userService.save(userDto));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[registration] Done");
        return responseEntity;
    }

    @Operation(summary = "Получение токена для зарегистрированного пользователя", description = "Возвращает логит и токен для зарегестрированного пользователя")
    @RequestMapping(value = "/token", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public TelResponseEntity<JwtResponse> authUser(@Valid @RequestBody CredentialsDto credentialsDto, HttpServletResponse response) {
        log.info("[authUser] Starting");
        TelResponseEntity<JwtResponse> responseEntity;

        try {
            responseEntity = new TelResponseOkEntity<>(authenticationService.authenticateAndGetToken(credentialsDto, response));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }
        log.info("[authUser] Done");
        return responseEntity;
    }
}
