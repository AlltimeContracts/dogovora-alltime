package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserDTO(
        UUID id,
        @NotBlank String firstName,
        @NotBlank String secondName,
        String thirdName,
        @NotBlank String position,
        @NotBlank String login,
        boolean isActive
) {
}
