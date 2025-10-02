package ru.alltime.dogovora.service;

import ru.alltime.dogovora.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findUserByLogin(String login);

    User findUserByRoles(String roles);

    User findUserByFirstName(String firstName);

    User createUser(User user);

    void deleteByLogin(String login);

    User updateUser(User user);

}