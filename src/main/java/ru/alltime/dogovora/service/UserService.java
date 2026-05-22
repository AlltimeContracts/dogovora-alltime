package ru.alltime.dogovora.service;

import ru.alltime.dogovora.dto.UserDTO;
import ru.alltime.dogovora.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> findAllUsers();

    UserDTO findUserById(UUID id);

    UserDTO findUserByLogin(String login);

    List<UserDTO> findUsersByFirstName(String firstName);

    UserDTO createUser(UserDTO userRequestDTO);

    void deleteById(UUID id);

    UserDTO updateUser(UserDTO userRequestDTO);
}
