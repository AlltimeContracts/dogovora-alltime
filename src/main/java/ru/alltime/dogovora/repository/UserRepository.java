package ru.alltime.dogovora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alltime.dogovora.model.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByFirstName(String firstName); // Метод для фильтрации по имени

    Optional<User> findByRole(String role); // Метод для фильтрации по роли

    Optional<User> findUserByLogin(String login);

    void delete(String login);
}
