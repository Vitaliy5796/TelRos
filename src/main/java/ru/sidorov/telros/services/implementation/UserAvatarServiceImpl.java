package ru.sidorov.telros.services.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.sidorov.telros.models.entities.UserDetailsInformation;
import ru.sidorov.telros.models.exception.NotFoundUserAvatarException;
import ru.sidorov.telros.repositories.UserDetailsInformationRepository;
import ru.sidorov.telros.services.abstracts.CommonService;
import ru.sidorov.telros.services.abstracts.UserAvatarService;

import java.io.ByteArrayInputStream;
import java.io.IOException;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserAvatarServiceImpl implements UserAvatarService {

    private final UserDetailsInformationRepository userRepository;
    private final CommonService commonService;


    @Override
    @Transactional
    public String addUserAvatar(MultipartFile file, UserDetailsInformation userDetailsInformation) throws IOException {
        String fullUrl = commonService.saveFile("user-avatars",
                userDetailsInformation.getId(),
                FilenameUtils.getExtension(file.getOriginalFilename()),
                new ByteArrayInputStream(file.getBytes()));
        userDetailsInformation.setPictureUrl(fullUrl);
        UserDetailsInformation saveUserDetailsInformation = userRepository.save(userDetailsInformation);

        return saveUserDetailsInformation.getPictureUrl();
    }

    @Override
    public String getUserAvatar(UserDetailsInformation userDetailsInformation) {
        String pictureUrl = userDetailsInformation.getPictureUrl();
        if (pictureUrl == null) {
            throw new NotFoundUserAvatarException(userDetailsInformation.getId());
        }
        return pictureUrl;
    }

    @Override
    @Transactional
    public void delUserAvatar(UserDetailsInformation userDetailsInformation) {
        userDetailsInformation.setPictureUrl(null);
        userRepository.save(userDetailsInformation);
    }
}
