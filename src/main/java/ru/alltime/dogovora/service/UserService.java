package ru.alltime.dogovora.service;

import ru.alltime.dogovora.dto.UserDTO;
import ru.alltime.dogovora.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    UserResponseDTO findUserByLogin(String login);

    List<UserResponseDTO> findUsersByRoles(String roles);

    List<UserResponseDTO> findUsersByFirstName(String firstName);

    UserResponseDTO createUser(UserDTO userRequestDTO);

    void deleteByLogin(String login);

    UserResponseDTO updateUser(UserDTO userRequestDTO);

}