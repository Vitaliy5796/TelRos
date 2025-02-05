package ru.sidorov.telros.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sidorov.telros.mappers.userDetailInformation.UserDetailsInformationMapper;
import ru.sidorov.telros.models.dto.userDetailsInformation.UserDetailsInformationDto;
import ru.sidorov.telros.models.dto.userDetailsInformation.UserDetailsInformationUpdateDto;
import ru.sidorov.telros.models.entities.User;
import ru.sidorov.telros.models.entities.UserDetailsInformation;
import ru.sidorov.telros.models.exception.AccessDeniedException;
import ru.sidorov.telros.models.exception.NotFoundUserDetailsInformationException;
import ru.sidorov.telros.models.exception.NotFoundUserException;
import ru.sidorov.telros.models.exception.NullIdException;
import ru.sidorov.telros.repositories.UserDetailsInformationRepository;
import ru.sidorov.telros.repositories.UserRepository;
import ru.sidorov.telros.services.abstracts.UserDetailsInformationService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsInformationServiceImpl implements UserDetailsInformationService {

    private final UserDetailsInformationRepository userDetailsInformationRepository;
    private final UserDetailsInformationMapper userDetailsInformationMapper;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetailsInformationDto save(UserDetailsInformationDto userDetailsInformationDto, Integer userId) {
        checkIdOnNull(userId);

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundUserException(userId));
        UserDetailsInformation userDetailsInformation = userDetailsInformationMapper.toUserDetailsInformation(userDetailsInformationDto);
        userDetailsInformation.setId(null);
        userDetailsInformation.setUser(user);
        UserDetailsInformation saveUserDetailsInformation = userDetailsInformationRepository.save(userDetailsInformation);

        return userDetailsInformationMapper.toUserDetailsInformationDto(saveUserDetailsInformation);
    }

    @Override
    public UserDetailsInformationDto getUserDetailsInformation(Integer detailsId, Integer userId) {
        checkIdOnNull(userId);
        checkIdOnNull(detailsId);
        UserDetailsInformation userDetailsInformation = getDetailsInformation(userDetailsInformationRepository.findUserDetailsInformationByIdAndUserId(detailsId, userId), userId);
        
        return userDetailsInformationMapper.toUserDetailsInformationDto(userDetailsInformation);
    }

    @Transactional
    @Override
    public UserDetailsInformationDto updateUserDetailsInformation(UserDetailsInformationUpdateDto userDto, Integer userId) {
        checkIdOnNull(userId);
        Integer detailsId = userDto.getId();
        checkIdOnNull(detailsId);

        UserDetailsInformation userDetailsInformation = getDetailsInformation(userDetailsInformationRepository.findById(detailsId), userId);
        checkAccess(userId, userDetailsInformation);

        userDetailsInformationMapper.updateFromUserContactInformationSaveDto(userDto, userDetailsInformation);

        UserDetailsInformation saveUserDetailsInformation = userDetailsInformationRepository.save(userDetailsInformation);

        return userDetailsInformationMapper.toUserDetailsInformationDto(saveUserDetailsInformation);
    }

    @Transactional
    @Override
    public void delUserDetailsInformation(Integer detailsId, Integer userId) {
        checkIdOnNull(userId);
        checkIdOnNull(detailsId);

        UserDetailsInformation userDetailsInformation = getDetailsInformation(userDetailsInformationRepository.findById(detailsId), userId);

        checkAccess(userId, userDetailsInformation);

        userDetailsInformationRepository.deleteUserDetailsInformationById(detailsId);

    }

    private static void checkAccess(Integer userId, UserDetailsInformation userDetailsInformation) {
        if (!userDetailsInformation.getUser().getId().equals(userId)) {
            throw new AccessDeniedException();
        }
    }

    private static void checkIdOnNull(Integer userId) {
        if (userId == null) {
            throw new NullIdException();
        }
    }

    private UserDetailsInformation getDetailsInformation(Optional<UserDetailsInformation> userDetailsInformationRepository, Integer userId) {
        return userDetailsInformationRepository.orElseThrow(() -> new NotFoundUserDetailsInformationException(userId));
    }
}
