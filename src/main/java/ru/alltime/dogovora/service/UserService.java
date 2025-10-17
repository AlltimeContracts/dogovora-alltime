package ru.alltime.dogovora.service;

import ru.alltime.dogovora.dto.UserResponseDTO;
import ru.alltime.dogovora.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    UserResponseDTO findUserByLogin(String login);

    List<UserResponseDTO> findUsersByRoles(String roles);

    List<User> findUsersByFirstName(String firstName);

    User createUser(User user);

    void deleteByLogin(String login);

    User updateUser(User user);

}