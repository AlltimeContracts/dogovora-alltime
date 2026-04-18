package ru.alltime.dogovora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alltime.dogovora.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findUsersByFirstName(String firstName); // Метод для фильтрации по имени

    List<User> findUsersByRole(String roles); // Метод для фильтрации по роли

    Optional<User> findUserByLogin(String login);

    void deleteByLogin(String login);

}
