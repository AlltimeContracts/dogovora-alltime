package ru.alltime.dogovora.dto;

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
