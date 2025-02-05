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
import ru.sidorov.telros.models.dto.userDetailsInformation.UserDetailsInformationDto;
import ru.sidorov.telros.models.dto.userDetailsInformation.UserDetailsInformationUpdateDto;
import ru.sidorov.telros.services.abstracts.UserDetailsInformationService;

@RestController
@RequestMapping("/api/user/{userId}/details")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Детальная информация", description = "API для работы с детальной информацией пользователя")
public class UserDetailsInformationRestController {

    private final UserDetailsInformationService userDetailsInformationService;

    @Operation(summary = "Создание детальной информации пользователя", description = "Возвращает детальную информацию пользователя")
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.POST)
    public TelResponseEntity<UserDetailsInformationDto> createUserDetailsInformation(@Valid @RequestBody UserDetailsInformationDto userDetailsInformationDto,
                                                                                     @PathVariable("userId") Integer userId) {
        log.info("[createUserDetailsInformation] Starting");
        TelResponseEntity<UserDetailsInformationDto> responseEntity;

        try {
            responseEntity = new TelResponseOkEntity<>(userDetailsInformationService.save(userDetailsInformationDto, userId));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[createUserDetailsInformation] Done");
        return responseEntity;
    }

    @Operation(summary = "Получение детальной информации пользователя", description = "Возвращает детальную информацию пользователя")
    @RequestMapping(value = "/{detailsId}", produces = "application/json", method = RequestMethod.GET)
    public TelResponseEntity<UserDetailsInformationDto> getUserDetailsInformation(@PathVariable("detailsId") Integer detailsId,
                                                                                  @PathVariable("userId") Integer userId) {
        log.info("[getUserDetailsInformation] Starting");
        TelResponseEntity<UserDetailsInformationDto> responseEntity;

        try {
            responseEntity = new TelResponseOkEntity<>(userDetailsInformationService.getUserDetailsInformation(detailsId, userId));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[getUserDetailsInformation] Done");
        return responseEntity;
    }

    @Operation(summary = "Обновление детальной информации о пользователе", description = "Возвращает обновленную детальную информацию о пользователе")
    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.PATCH)
    public TelResponseEntity<UserDetailsInformationDto> updateUserDetailsInformation(@Valid @RequestBody UserDetailsInformationUpdateDto userDto,
                                                                   @PathVariable("userId") Integer userId) {
        log.info("[updateUserDetailsInformation] Starting");
        TelResponseEntity<UserDetailsInformationDto> responseEntity;

        try {
            responseEntity = new TelResponseOkEntity<>(userDetailsInformationService.updateUserDetailsInformation(userDto, userId));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[updateUserDetailsInformation] Done");
        return responseEntity;
    }

    @Operation(summary = "Удаление детальной информации пользователя", description = "Возвращает строку с информации об успешности операции")
    @RequestMapping(value = "/{detailsId}", produces = "application/json", method = RequestMethod.DELETE)
    public TelResponseEntity<String> delUser(@PathVariable("detailsId") Integer detailsId,
                                             @PathVariable("userId") Integer userId) {
        log.info("[delUser] Starting");
        TelResponseEntity<String> responseEntity;

        try {
            userDetailsInformationService.delUserDetailsInformation(detailsId, userId);
            responseEntity = new TelResponseOkEntity<>(String.format("Детальная информация с id: %d пользователя с id: %d успешно удалена", detailsId, userId));
        } catch (Exception e) {
            log.error(ExceptionUtils.getRootCauseMessage(e));
            responseEntity = new TelResponseErrorEntity<>(e);
        }

        log.info("[delUser] Done");
        return responseEntity;
    }
}
