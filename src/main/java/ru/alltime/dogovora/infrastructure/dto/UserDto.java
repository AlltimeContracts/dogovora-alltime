package ru.alltime.dogovora.infrastructure.dto;

import lombok.*;
import ru.alltime.dogovora.domain.models.Roles;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserDto {
    private String id;
    private Boolean active;
    private String login;
    private String firstName;
    private String secondName;
    private String thirdName;
    private Roles role;   // <-- enum в DTO
    private String position;
}