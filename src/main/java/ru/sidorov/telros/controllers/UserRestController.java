package ru.sidorov.telros.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;
import ru.sidorov.telros.models.dto.common.TelResponseEntity;
import ru.sidorov.telros.models.dto.common.TelResponseErrorEntity;
import ru.sidorov.telros.models.dto.common.TelResponseOkEntity;
import ru.sidorov.telros.models.dto.user.UserContactInformationDto;
import ru.sidorov.telros.models.dto.user.UserContactInformationSaveDto;
import ru.sidorov.telros.models.dto.user.UserContactInformationUpdateDto;
import ru.sidorov.telros.services.abstracts.UserContactInformationService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Пользователь", description = "API для работы с пользователем")
public class UserRestController {

    private final UserContactInformationService userContactInformationService;

    @Operation(summary = "Создание нового пользователя", description = "Возвращает нового пользователя")
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.POST)
    public TelResponseEntity<UserContactInformationDto> createUser(@Valid @RequestBody UserContactInformationSaveDto userContactInformationSaveDto) {
        log.info("[createUser] Starting");
        TelResponseEntity<UserContactInformationDto> responseEntity;

        try {
            responseEntity = new TelResponseOkEntity<>(userContactInformationService.save(userContactInformationSaveDto));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[createUser] Done");
        return responseEntity;
    }

    @Operation(summary = "Получение пользователя", description = "Возвращает пользователя")
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.GET)
    public TelResponseEntity<UserContactInformationDto> gatUser(@PathVariable("id") Integer id) {
        log.info("[gatUser] Starting");
        TelResponseEntity<UserContactInformationDto> responseEntity;

        try {
            responseEntity = new TelResponseOkEntity<>(userContactInformationService.getUser(id));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[gatUser] Done");
        return responseEntity;
    }

    @Operation(summary = "Обновление информации о пользователе", description = "Возвращает обновленную информацию о пользователе")
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.PATCH)
    public TelResponseEntity<UserContactInformationDto> updateUser(@Valid @RequestBody UserContactInformationUpdateDto userDto) {
        log.info("[updateUser] Starting");
        TelResponseEntity<UserContactInformationDto> responseEntity;

        try {
            responseEntity = new TelResponseOkEntity<>(userContactInformationService.updateUser(userDto));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[updateUser] Done");
        return responseEntity;
    }

    @Operation(summary = "Удаление своего пользователя", description = "Возвращает строку с информации об успешности операции")
    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.DELETE)
    public TelResponseEntity<String> delUser(@PathVariable("id") Integer id) {
        log.info("[delUser] Starting");
        TelResponseEntity<String> responseEntity;

        try {
            userContactInformationService.delUser(id);
            responseEntity = new TelResponseOkEntity<>(String.format("Пользователь с id: %d успешно удален", id));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[delUser] Done");
        return responseEntity;
    }
}
