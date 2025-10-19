package ru.alltime.dogovora.service;

import ru.alltime.dogovora.dto.UserRequestDTO;
import ru.alltime.dogovora.dto.UserResponseDTO;
import ru.alltime.dogovora.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    UserResponseDTO findUserByLogin(String login);

    List<UserResponseDTO> findUsersByRoles(String roles);

    List<UserResponseDTO> findUsersByFirstName(String firstName);

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    void deleteByLogin(String login);

    UserResponseDTO updateUser(UserRequestDTO userRequestDTO);

}