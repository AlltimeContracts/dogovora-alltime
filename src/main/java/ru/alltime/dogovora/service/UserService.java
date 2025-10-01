package ru.alltime.dogovora.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.alltime.dogovora.model.User;

import java.util.List;


public interface UserService {

    List<User> findAllUsers();

    User findUserByLogin(String login);

    User findUserByRole(String role);

    User findUserByFirstName(String firstName);

    User createUser(User user);

    void deleteUser(String login);

    User updateUser(User user);


}
