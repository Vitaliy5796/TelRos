package ru.sidorov.telros.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sidorov.telros.models.entities.UserDetailsInformation;

import java.util.Optional;

@Repository
public interface UserDetailsInformationRepository extends BaseRepository<UserDetailsInformation>{

    Optional<UserDetailsInformation> findUserDetailsInformationByIdAndUserId(Integer id, Integer userId);

    @Modifying
    @Query("DELETE FROM UserDetailsInformation udi WHERE udi.id = :id")
    void deleteUserDetailsInformationById(Integer id);
}
