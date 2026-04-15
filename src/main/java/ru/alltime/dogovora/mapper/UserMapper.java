package ru.alltime.dogovora.mapper;

import lombok.*;
import org.springframework.stereotype.Component;
import ru.alltime.dogovora.dto.UserDTO;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDTO toDto(UserEntity userEntity) {

        return UserDTO.builder()
                .id(userEntity.getId().toString())
                .firstName(userEntity.getFirstName())
                .secondName(userEntity.getSecondName())
                .thirdName(userEntity.getThirdName())
                .position(userEntity.getPosition())
                .login(userEntity.getLogin())
                .isActive(userEntity.getIsActive())
                .build();
    }
    public List<UserDTO> toDto(List<UserEntity> userEntities) {
        return userEntities.stream().map(this::toDto).toList();
    }
}
