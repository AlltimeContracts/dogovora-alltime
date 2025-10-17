package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alltime.dogovora.dto.UserResponseDTO;
import ru.alltime.dogovora.mapper.UserMapper;
import ru.alltime.dogovora.model.User;
import ru.alltime.dogovora.repository.UserRepository;

import java.util.List;

@Service
@Slf4j

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponseDTO findUserByLogin(String login) {
       User user =  userRepository.findUserByLogin(login).orElseThrow(() -> new EntityNotFoundException());
        return userMapper.toResponseDto(user);
    }

    @Override
    public List<UserResponseDTO> findUsersByRoles(String roles) {
        return userRepository.findUsersByRoles(roles)
                .stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    @Override
    public  List<User> findUsersByFirstName(String firstName) {
        return userRepository.findUsersByFirstName(firstName);
    }

    @Override
    public User createUser(User user) {
        userRepository.save(user);
        log.info("User saved: {}", user);
        return user;
    }

    @Override
    public void deleteByLogin(String login) {
        userRepository.deleteByLogin(login);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
