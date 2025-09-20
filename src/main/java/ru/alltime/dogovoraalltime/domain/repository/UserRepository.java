package ru.alltime.dogovoraalltime.domain.repository;

import ru.alltime.dogovoraalltime.domain.models.User;

public interface UserRepository {
    User getUserById(int id);
    User findByUsername(String username);
}
