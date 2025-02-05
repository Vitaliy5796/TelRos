package ru.sidorov.telros.services.implementation;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sidorov.telros.mappers.user.UserMapper;
import ru.sidorov.telros.models.dto.user.UserContactInformationDto;
import ru.sidorov.telros.models.dto.user.UserContactInformationSaveDto;
import ru.sidorov.telros.models.dto.user.UserContactInformationUpdateDto;
import ru.sidorov.telros.models.entities.Role;
import ru.sidorov.telros.models.entities.User;
import ru.sidorov.telros.models.exception.*;
import ru.sidorov.telros.repositories.RoleRepository;
import ru.sidorov.telros.repositories.UserRepository;
import ru.sidorov.telros.services.abstracts.UserContactInformationService;

@Service
@RequiredArgsConstructor
public class UserContactInformationServiceImpl implements UserContactInformationService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserContactInformationDto save(UserContactInformationSaveDto userDto) {
        String email = userDto.getEmail();
        checkAlreadyExistsEmail(email);

        String contactPhone = userDto.getContactPhone();
        checkAlreadyExistsPhone(contactPhone);

        User user = userMapper.toUser(userDto);
        user.setId(null);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findById(1).orElseThrow(() -> new NotFoundRoleException(1));
        user.setRole(role);
        User saveUser = userRepository.save(user);

        return userMapper.toUserContactInformationDto(saveUser);
    }

    @Override
    public UserContactInformationDto getUser(Integer id) {
        return userMapper.toUserContactInformationDto(userRepository.findById(id).orElseThrow(() -> new NotFoundUserException(id)));
    }

    @Transactional
    @Override
    public UserContactInformationDto updateUser(UserContactInformationUpdateDto userDto) {
        Integer userId = userDto.getId();
        if (userId == null) {
            throw new NullIdException();
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundUserException(userId));
        String email = userDto.getEmail();
        String contactPhone = userDto.getContactPhone();

        if (email != null && !email.equals(user.getEmail())) {
            checkAlreadyExistsEmail(email);
        }
        if (contactPhone != null && !contactPhone.equals(user.getContactPhone())) {
            checkAlreadyExistsPhone(contactPhone);
        }

        userMapper.updateFromUserContactInformationUpdateDto(userDto, user);

        if (userDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        User saveUser = userRepository.save(user);

        return userMapper.toUserContactInformationDto(saveUser);
    }

    @Transactional
    @Override
    public void delUser(Integer id) {
        if (id == null) {
            throw new NullIdException();
        }

        if (!userRepository.existsById(id)) {
            throw new NotFoundUserException(id);
        }

        userRepository.deleteById(id);
    }

    private void checkAlreadyExistsEmail(String email) {
        if (!StringUtils.isEmpty(email) && userRepository.existsByEmail(email)) {
            throw new UserWithEmailAlreadyExistsException(email);
        }
    }

    private void checkAlreadyExistsPhone(String phone) {
        if (!StringUtils.isEmpty(phone) && userRepository.existsByContactPhone(phone)) {
            throw new UserWithPhoneAlreadyExistsException(phone);
        }
    }
}
