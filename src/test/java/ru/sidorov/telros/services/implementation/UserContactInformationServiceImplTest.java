package ru.sidorov.telros.services.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.sidorov.telros.mappers.user.UserMapper;
import ru.sidorov.telros.models.dto.user.UserContactInformationDto;
import ru.sidorov.telros.models.dto.user.UserContactInformationSaveDto;
import ru.sidorov.telros.models.dto.user.UserContactInformationUpdateDto;
import ru.sidorov.telros.models.entities.Role;
import ru.sidorov.telros.models.entities.User;
import ru.sidorov.telros.models.exception.NotFoundUserException;
import ru.sidorov.telros.models.exception.UserWithEmailAlreadyExistsException;
import ru.sidorov.telros.models.exception.UserWithPhoneAlreadyExistsException;
import ru.sidorov.telros.repositories.RoleRepository;
import ru.sidorov.telros.repositories.UserRepository;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserContactInformationServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserContactInformationServiceImpl userService;

    private User user;
    private Role role;
    private UserContactInformationSaveDto userSaveDto;
    private UserContactInformationUpdateDto userUpdateDto;
    private UserContactInformationDto userDto;

    @BeforeEach
    void setUp() {
        role = new Role(1, "ROLE_USER");
        user = new User(1, "encodedPassword", "user@mail.ru", "+79999999999", null, role);

        userSaveDto = new UserContactInformationSaveDto("user@mail.ru", "+79999999999", "password");
        userUpdateDto = new UserContactInformationUpdateDto(1, "new@mail.ru", "+78888888888", "newpassword");
        userDto = new UserContactInformationDto(1, "user@mail.ru", "+79999999999", null);
    }

    @Test
    void saveUser_EmailAlreadyExists() {
        when(userRepository.existsByEmail(userSaveDto.getEmail())).thenReturn(true);

        assertThrows(UserWithEmailAlreadyExistsException.class, () -> userService.save(userSaveDto));
    }

    @Test
    void saveUser_PhoneAlreadyExists() {
        when(userRepository.existsByContactPhone(userSaveDto.getContactPhone())).thenReturn(true);

        assertThrows(UserWithPhoneAlreadyExistsException.class, () -> userService.save(userSaveDto));
    }

    @Test
    void getUser_Success() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userMapper.toUserContactInformationDto(user)).thenReturn(userDto);

        UserContactInformationDto result = userService.getUser(1);

        assertNotNull(result);
        assertEquals(userDto.getEmail(), result.getEmail());
        assertEquals(userDto.getContactPhone(), result.getContactPhone());
    }

    @Test
    void getUser_NotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundUserException.class, () -> userService.getUser(1));
    }

    @Test
    void updateUser_NotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundUserException.class, () -> userService.updateUser(userUpdateDto));
    }

    @Test
    void deleteUser_Success() {
        when(userRepository.existsById(1)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1);

        assertDoesNotThrow(() -> userService.delUser(1));
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteUser_NotFound() {
        when(userRepository.existsById(1)).thenReturn(false);

        assertThrows(NotFoundUserException.class, () -> userService.delUser(1));
    }
}

