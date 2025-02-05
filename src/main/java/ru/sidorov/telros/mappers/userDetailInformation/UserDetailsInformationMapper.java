package ru.sidorov.telros.mappers.userDetailInformation;

import org.mapstruct.*;
import ru.sidorov.telros.models.dto.userDetailsInformation.UserDetailsInformationDto;
import ru.sidorov.telros.models.dto.userDetailsInformation.UserDetailsInformationUpdateDto;
import ru.sidorov.telros.models.entities.UserDetailsInformation;

@Mapper(componentModel = "spring")
public interface UserDetailsInformationMapper {


    /**
     * map {@link UserDetailsInformation} to {@link UserDetailsInformationDto}
     *
     * @param userDetailsInformation {@link UserDetailsInformation} object
     * @return {@link UserDetailsInformationDto}
     */
    UserDetailsInformationDto toUserDetailsInformationDto(UserDetailsInformation userDetailsInformation);

    /**
     * map {@link UserDetailsInformationDto} to {@link UserDetailsInformation}
     *
     * @param userDetailsInformationDto {@link UserDetailsInformationDto} object
     * @return {@link UserDetailsInformation}
     */
    UserDetailsInformation toUserDetailsInformation(UserDetailsInformationDto userDetailsInformationDto);

    /**
     * update {@link UserDetailsInformation} by {@link UserDetailsInformationUpdateDto}
     *
     * @param userDetailsInformationUpdateDto {@link UserDetailsInformationUpdateDto} object
     * @param userDetailsInformation {@link UserDetailsInformation}
     */
    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    void updateFromUserContactInformationSaveDto(UserDetailsInformationUpdateDto userDetailsInformationUpdateDto, @MappingTarget UserDetailsInformation userDetailsInformation);
}
