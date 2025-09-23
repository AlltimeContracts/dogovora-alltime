package ru.alltime.dogovora.infrastructure.dto;

import ru.alltime.dogovora.domain.models.User;

public final class UserDtoMapper {
    public static User toDomain(UserDto d) {
        if (d == null) return null;
        return User.builder()
                .id(d.getId())
                .active(d.getActive() == null ? true : d.getActive())
                .login(d.getLogin())
                .firstName(d.getFirstName())
                .secondName(d.getSecondName())
                .thirdName(d.getThirdName())
                .role(d.getRole())            // enum напрямую
                .position(d.getPosition())
                .build();
    }

    public static UserDto toDto(User u) {
        if (u == null) return null;
        return UserDto.builder()
                .id(u.getId())
                .active(u.isActive())
                .login(u.getLogin())
                .firstName(u.getFirstName())
                .secondName(u.getSecondName())
                .thirdName(u.getThirdName())
                .role(u.getRole())            // enum напрямую
                .position(u.getPosition())
                .build();
    }

    private UserDtoMapper() {}
}