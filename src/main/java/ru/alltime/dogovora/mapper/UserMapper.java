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
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .thirdName(user.getThirdName())
                .position(user.getPosition())
                .login(user.getLogin())
                .isActive(user.isActive())
                .build();
    }

    public List<UserDTO> toDto(List<User> users) {
        return users.stream().map(this::toDto).toList();
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setSecondName(dto.getSecondName());
        user.setThirdName(dto.getThirdName());
        user.setPosition(dto.getPosition());
        user.setLogin(dto.getLogin());
        user.setActive(dto.isActive());
        return user;
    }
}
