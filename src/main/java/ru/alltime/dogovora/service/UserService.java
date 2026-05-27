package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.alltime.dogovora.dto.UserDTO;
import ru.alltime.dogovora.mapper.UserMapper;
import ru.alltime.dogovora.model.Role;
import ru.alltime.dogovora.model.User;
import ru.alltime.dogovora.repository.UserRepository;
import ru.alltime.dogovora.security.JWTService;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public UserDTO findUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return userMapper.toDto(user);
    }

    public UserDTO findUserByLogin(String login) {
        User user = userRepository.findUserByLogin(login).orElseThrow(EntityNotFoundException::new);
        return userMapper.toDto(user);
    }

    public List<UserDTO> findUsersByFirstName(String firstName) {
        List<User> users = userRepository.findUsersByFirstName(firstName);
        return users.stream().map(userMapper::toDto).toList();
    }

    public UserDTO createUser(UserDTO userRequestDTO) {
        User userEntity = userMapper.toEntity(userRequestDTO);
        userEntity.setRole(Role.MANAGER);
        userEntity.setPassword(encoder.encode(userEntity.getPassword()));

        User user = userRepository.save(userEntity);
        log.info("User saved: {}", user);
        return userMapper.toDto(user);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

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

    public String verify(UserDTO userDTO) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.login(), userDTO.password()));

        if (authentication.isAuthenticated())
            return jwtService.generateToken(userDTO.login());

        return "Fail";
    }
}
