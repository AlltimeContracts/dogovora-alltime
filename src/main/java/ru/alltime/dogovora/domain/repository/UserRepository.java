package ru.alltime.dogovora.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.alltime.dogovora.domain.models.User;

import java.util.Optional;

public interface UserRepository {
    Page<User> findAll(String search, Pageable pageable);
    Optional<User> findById(String id);
    Optional<User> findByUsername(String username); // <— важно!
    User save(User user);
    void softDelete(String id); // <— важно!
}
