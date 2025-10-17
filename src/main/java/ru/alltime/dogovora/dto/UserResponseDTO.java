package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UserResponseDTO(
        @Null UUID id,
        @NotEmpty String firstName,
        @NotEmpty String secondName,
        String thirdName,
        String position,
        boolean isActive,
        @Size(min = 5, max = 20) String login
) {
}
