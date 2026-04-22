package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alltime.dogovora.dto.UserDTO;
import ru.alltime.dogovora.mapper.UserMapper;
import ru.alltime.dogovora.model.User;
import ru.alltime.dogovora.repository.UserRepository;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO findUserByLogin(String login) {
        User user = userRepository.findUserByLogin(login).orElseThrow(EntityNotFoundException::new);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDTO> findUsersByRoles(String roles) {
        return userRepository.findUsersByRoles(roles).stream().map(userMapper::toDto).toList();
    }

    @Override
    public List<UserDTO> findUsersByFirstName(String firstName) {
        List<User> users = userRepository.findUsersByFirstName(firstName);
        return users.stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserDTO createUser(UserDTO userRequestDTO) {
        User user = userRepository.save(userMapper.toEntity(userRequestDTO));
        log.info("User saved: {}", user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteByLogin(String login) {
        userRepository.deleteByLogin(login);
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserDTO userRequestDTO) {
        User existingUser = userRepository.findUserByLogin(userRequestDTO.getLogin()).orElseThrow(EntityNotFoundException::new);

        existingUser.setFirstName(userRequestDTO.getFirstName());
        existingUser.setSecondName(userRequestDTO.getSecondName());
        existingUser.setThirdName(userRequestDTO.getThirdName());
        existingUser.setPosition(userRequestDTO.getPosition());
        existingUser.setLogin(userRequestDTO.getLogin());
        existingUser.setActive(userRequestDTO.isActive());

        userRepository.save(existingUser);
        log.info("User updated: {}", existingUser);
        return userMapper.toDto(existingUser);
    }
}
