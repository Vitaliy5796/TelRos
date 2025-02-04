package ru.sidorov.telros.services.implementation;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sidorov.telros.mappers.user.UserMapper;
import ru.sidorov.telros.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    public UserRepository userRepository;

    @Mock
    public UserMapper userMapper;

    @InjectMocks
    public UserServiceImpl userService;
}
