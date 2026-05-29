package ru.alltime.dogovora.dto.userDTOs;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterDTO(
        @NotBlank String login,
        @NotBlank String password,
        @NotBlank String firstName,
        String secondName,
        String thirdName,
        String position
) {
}
