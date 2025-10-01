package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alltime.dogovora.model.User;
import ru.alltime.dogovora.repository.UserRepository;

import java.util.List;

@Service
@Slf4j

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public User findUserByRole(String role) {
        return userRepository.findByRole(role).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public User findUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public User createUser(User user) {
        userRepository.save(user);
        log.info("User saved: {}", user);
        return user;
    }

    @Override
    public void deleteUser(String login) {
        userRepository.delete(login);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
