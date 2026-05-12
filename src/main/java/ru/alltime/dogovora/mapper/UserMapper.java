package ru.alltime.dogovora.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alltime.dogovora.dto.UserDTO;
import ru.alltime.dogovora.model.User;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDTO toDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getSecondName(),
                user.getThirdName(),
                user.getPosition(),
                user.getLogin(),
                user.isActive()
        );
    }

    public List<UserDTO> toDto(List<User> users) {
        return users.stream().map(this::toDto).toList();
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.id());
        user.setFirstName(dto.firstName());
        user.setSecondName(dto.secondName());
        user.setThirdName(dto.thirdName());
        user.setPosition(dto.position());
        user.setLogin(dto.login());
        user.setActive(dto.isActive());
        return user;
    }
}
