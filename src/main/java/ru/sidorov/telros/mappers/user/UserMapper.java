package ru.sidorov.telros.mappers.user;

import org.mapstruct.*;
import ru.sidorov.telros.mappers.userDetailInformation.UserDetailsInformationMapper;
import ru.sidorov.telros.models.dto.user.UserContactInformationDto;
import ru.sidorov.telros.models.dto.user.UserContactInformationSaveDto;
import ru.sidorov.telros.models.dto.user.UserContactInformationUpdateDto;
import ru.sidorov.telros.models.dto.user.UserDto;
import ru.sidorov.telros.models.entities.User;

/**
 * Маппер для пользователя
 */
@Mapper(componentModel = "spring", uses = {UserDetailsInformationMapper.class})
public interface UserMapper {


    /**
     * map {@link User} to {@link UserContactInformationDto}
     *
     * @param user {@link User} object
     * @return {@link UserContactInformationDto}
     */
    UserContactInformationDto toUserContactInformationDto(User user);

    /**
     * map {@link User} to {@link UserDto}
     *
     * @param user {@link User} object
     * @return {@link UserDto}
     */
    UserDto toUserDto(User user);

    /**
     * map {@link UserContactInformationSaveDto} to {@link User}
     *
     * @param userContactInformationSaveDto {@link UserContactInformationSaveDto} object
     * @return {@link User}
     */
    User toUser(UserContactInformationSaveDto userContactInformationSaveDto);


    /**
     * update {@link User} by {@link UserContactInformationUpdateDto}
     *
     * @param userContactInformationUpdateDto {@link UserContactInformationUpdateDto} object
     * @param user {@link User}
     */
    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void updateFromUserContactInformationUpdateDto(UserContactInformationUpdateDto userContactInformationUpdateDto, @MappingTarget User user);
}
