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
    public  List<UserResponseDTO> findUsersByFirstName(String firstName) {
        List<User> users =  userRepository.findUsersByFirstName(firstName);
        return users.stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    @Override
    public UserResponseDTO createUser(UserDTO userRequestDTO) {
         User user = userRepository.save(userMapper.toEntity(userRequestDTO));
        log.info("User saved: {}", user);
        return userMapper.toResponseDto(user);
    }

    @Override
    public void deleteByLogin(String login) {
        userRepository.deleteByLogin(login);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(UserDTO userRequestDTO) {
        User existingUser =  userRepository.findUserByLogin(userRequestDTO.login()).orElseThrow(() -> new EntityNotFoundException());

        existingUser.setFirstName(userRequestDTO.firstName());
        existingUser.setSecondName(userRequestDTO.secondName());
        existingUser.setThirdName(userRequestDTO.thirdName());
        existingUser.setPosition(userRequestDTO.position());
        existingUser.setLogin(userRequestDTO.login());

        userRepository.save(existingUser);
        log.info("User updated: {}", existingUser);
        return userMapper.toResponseDto(existingUser);
    }


}
