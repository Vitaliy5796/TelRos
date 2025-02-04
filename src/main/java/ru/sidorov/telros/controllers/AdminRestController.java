package ru.sidorov.telros.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import ru.sidorov.telros.models.dto.user.UserDto;
import ru.sidorov.telros.models.dto.user.UserSaveDto;
import ru.sidorov.telros.services.abstracts.UserService;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Администратор", description = "API администратора для работы с пользователями")
public class AdminRestController {

    private final UserService userService;

    @Operation(summary = "Создание нового пользователя", description = "Возвращает созданного пользователя")
    @RequestMapping(value = "/newUser", produces = "application/json", method = RequestMethod.POST)
    public TelResponseEntity<UserDto> createUser(@RequestBody UserSaveDto userDto) {
        log.info("[createUser] Starting");
        TelResponseEntity<UserDto> responseEntity;

        try {
            responseEntity = new TelResponseOkEntity<>(userService.save(userDto));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[createUser] Done");
        return responseEntity;
    }
}
