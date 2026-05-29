package ru.alltime.dogovora.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alltime.dogovora.dto.userDTOs.UserRegisterDTO;
import ru.alltime.dogovora.dto.userDTOs.UserResponseDTO;
import ru.alltime.dogovora.model.User;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserResponseDTO toDto(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getLogin(),
                user.getFirstName(),
                user.getSecondName(),
                user.getThirdName(),
                user.getPosition(),
                user.isActive()
        );
    }

    public List<UserResponseDTO> toDto(List<User> users) {
        return users.stream().map(this::toDto).toList();
    }

    public User toEntity(UserRegisterDTO dto) {
        User user = new User();
        user.setFirstName(dto.firstName());
        user.setSecondName(dto.secondName());
        user.setThirdName(dto.thirdName());
        user.setPosition(dto.position());
        user.setLogin(dto.login());
        user.setPassword(dto.password());
        return user;
    }
}
