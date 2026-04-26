package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Data
@Builder
public class UserDTO{
        private UUID id;
        @NotBlank private String firstName;
        @NotBlank private String secondName;
        private String thirdName;
        @NotBlank private String position;
        @NotBlank private String login;
        private boolean isActive;
}
