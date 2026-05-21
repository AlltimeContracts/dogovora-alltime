package ru.alltime.dogovora.service;

import ru.alltime.dogovora.dto.UserDTO;
import ru.alltime.dogovora.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    UserDTO findUserByLogin(String login);

    List<UserDTO> findUsersByFirstName(String firstName);

    UserDTO createUser(UserDTO userRequestDTO);

    void deleteByLogin(String login);

    UserDTO updateUser(UserDTO userRequestDTO);
}
