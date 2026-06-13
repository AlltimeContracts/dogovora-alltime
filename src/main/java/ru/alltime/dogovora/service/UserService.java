package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.alltime.dogovora.dto.JwtTokenDTO;
import ru.alltime.dogovora.dto.userDTOs.UserRegisterDTO;
import ru.alltime.dogovora.dto.userDTOs.UserResponseDTO;
import ru.alltime.dogovora.mapper.UserMapper;
import ru.alltime.dogovora.model.Role;
import ru.alltime.dogovora.model.User;
import ru.alltime.dogovora.repository.UserRepository;
import ru.alltime.dogovora.security.jwt.JWTService;

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

    public List<UserResponseDTO> findAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    public UserResponseDTO findUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return userMapper.toDto(user);
    }

    public UserResponseDTO findUserByLogin(String login) {
        User user = userRepository.findByLogin(login).orElseThrow(EntityNotFoundException::new);
        return userMapper.toDto(user);
    }

    public List<UserResponseDTO> findUsersByFirstName(String firstName) {
        List<User> users = userRepository.findUsersByFirstName(firstName);
        return users.stream().map(userMapper::toDto).toList();
    }

    public UserResponseDTO createUser(UserRegisterDTO userRequestDTO) {
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

    public UserResponseDTO updateUser(UserResponseDTO userRequestDTO) {
        User existingUser = userRepository.findById(userRequestDTO.id()).orElseThrow(EntityNotFoundException::new);

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

    public JwtTokenDTO verify(UserRegisterDTO userDTO) {
        try {
            final var userLogin = userDTO.login();
            authManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin, userDTO.password()));
            String accessToken = jwtService.generateAccessToken(userLogin);
            String refreshToken = jwtService.generateRefreshToken(userLogin);
            return new JwtTokenDTO(accessToken, refreshToken);
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }

    public JwtTokenDTO refreshAccessToken(String refreshToken) {
        if (!jwtService.validateRefreshToken(refreshToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired refresh token");
        }

        String login = jwtService.extractLogin(refreshToken);
        return new JwtTokenDTO(jwtService.generateAccessToken(login), null);
    }
}
