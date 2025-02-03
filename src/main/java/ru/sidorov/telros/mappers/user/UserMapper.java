package ru.sidorov.telros.mappers.user;

import org.mapstruct.*;
import ru.sidorov.telros.models.dto.user.UserDto;
import ru.sidorov.telros.models.dto.user.UserSaveDto;
import ru.sidorov.telros.models.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {


    /**
     * map {@link User} to {@link UserDto}
     *
     * @param user {@link User} object
     * @return {@link UserDto}
     */
    UserDto toUserDto(User user);

    /**
     * map {@link UserSaveDto} to {@link User}
     *
     * @param userSaveDto {@link UserSaveDto} object
     * @return {@link User}
     */
    User toUser(UserSaveDto userSaveDto);

    /**
     * update {@link User} by {@link UserSaveDto}
     *
     * @param userSaveDto {@link UserSaveDto} object
     * @param user {@link User}
     */
    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void updateFromSaveDto(UserSaveDto userSaveDto, @MappingTarget User user);
}
