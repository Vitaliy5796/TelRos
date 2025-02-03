package ru.sidorov.telros.services.abstracts;

import org.springframework.web.multipart.MultipartFile;
import ru.sidorov.telros.models.dto.user.UserDto;
import ru.sidorov.telros.models.dto.user.UserSaveDto;
import ru.sidorov.telros.models.entities.User;

import java.io.IOException;

public interface UserService {

    /**
     * Save user in database
     *
     * @param userDto {@link UserSaveDto}
     * @return {@link UserDto}
     */
    UserDto save(UserSaveDto userDto);

    /**
     * Add user avatar
     *
     * @param file {@link MultipartFile}
     * @param user {@link User}
     * @return {@link UserDto}
     */
    UserDto addUserAvatar(MultipartFile file, User user) throws IOException;

    /**
     * Delete user avatar
     *
     * @param user {@link User}
     */
    void delUserAvatar(User user);

    /**
     * Get user avatar
     *
     * @param user {@link User}
     * @return {@link String}
     */
    String getUserAvatar(User user);

    /**
     * Update user
     *
     * @param userDto {@link UserSaveDto}
     * @return {@link UserDto}
     */
    UserDto updateByDto(UserSaveDto userDto);

    /**
     * Get user by id
     *
     * @param id {@link Integer}
     * @return {@link User}
     */
    User getUser(Integer id);
}
