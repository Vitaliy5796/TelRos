package ru.sidorov.telros.services.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.sidorov.telros.mappers.user.UserMapper;
import ru.sidorov.telros.models.dto.user.UserDto;
import ru.sidorov.telros.models.dto.user.UserSaveDto;
import ru.sidorov.telros.models.entities.Role;
import ru.sidorov.telros.models.entities.User;
import ru.sidorov.telros.models.exception.*;
import ru.sidorov.telros.repositories.RoleRepository;
import ru.sidorov.telros.repositories.UserRepository;
import ru.sidorov.telros.services.abstracts.CommonService;
import ru.sidorov.telros.services.abstracts.UserService;

import java.io.ByteArrayInputStream;
import java.io.IOException;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final CommonService commonService;


    @Override
    @Transactional
    public UserDto save(UserSaveDto userDto) {

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

        return userMapper.toUserDto(saveUser);
    }

    @Transactional
    @Override
    public UserDto updateByDto(UserSaveDto userDto) {
        Integer userId = userDto.getId();
        if (userId == null) {
            throw new NullIdException();
        }
        User user = this.getUser(userId);
        String email = userDto.getEmail();
        String contactPhone = userDto.getContactPhone();

        if (email != null && !email.equals(user.getEmail())) {
            checkAlreadyExistsEmail(email);
        }
        if (contactPhone != null && !contactPhone.equals(user.getContactPhone())) {
            checkAlreadyExistsPhone(contactPhone);
        }

        userMapper.updateFromSaveDto(userDto, user);
        User saveUser = userRepository.save(user);

        return userMapper.toUserDto(saveUser);
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundUserException(id));
    }


    @Override
    @Transactional
    public UserDto addUserAvatar(MultipartFile file, User user) throws IOException {
        String fullUrl = commonService.saveFile("user-avatars",
                user.getId(),
                FilenameUtils.getExtension(file.getOriginalFilename()),
                new ByteArrayInputStream(file.getBytes()));
        user.setPictureUrl(fullUrl);
        User saveUser = userRepository.save(user);

        return userMapper.toUserDto(saveUser);
    }

    @Override
    public String getUserAvatar(User user) {
        String pictureUrl = user.getPictureUrl();
        if (pictureUrl == null) {
            throw new NotFoundUserAvatarException(user.getId());
        }
        return pictureUrl;
    }

    @Override
    @Transactional
    public void delUserAvatar(User user) {
        user.setPictureUrl(null);
        userRepository.save(user);
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
