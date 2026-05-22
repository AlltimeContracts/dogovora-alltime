package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.alltime.dogovora.dto.UserDTO;
import ru.alltime.dogovora.mapper.UserMapper;
import ru.alltime.dogovora.model.Role;
import ru.alltime.dogovora.model.User;
import ru.alltime.dogovora.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO findUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO findUserByLogin(String login) {
        User user = userRepository.findUserByLogin(login).orElseThrow(EntityNotFoundException::new);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserDTO> findUsersByFirstName(String firstName) {
        List<User> users = userRepository.findUsersByFirstName(firstName);
        return users.stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserDTO createUser(UserDTO userRequestDTO) {
        User userEntity = userMapper.toEntity(userRequestDTO);
        //TODO исправить ошибки IDE (всё красное!)
        userEntity.setRole(Role.MANAGER);
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));

        User user = userRepository.save(userEntity);
        log.info("User saved: {}", user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserDTO userRequestDTO) {
        User existingUser = userRepository.findUserByLogin(userRequestDTO.login()).orElseThrow(EntityNotFoundException::new);

        existingUser.setFirstName(userRequestDTO.firstName());
        existingUser.setSecondName(userRequestDTO.secondName());
        existingUser.setThirdName(userRequestDTO.thirdName());
        existingUser.setPosition(userRequestDTO.position());
        existingUser.setLogin(userRequestDTO.login());
        existingUser.setActive(userRequestDTO.isActive());

        userRepository.save(existingUser);
        log.info("User updated: {}", existingUser);
        return userMapper.toDto(existingUser);
    }
}
