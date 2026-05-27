package ru.alltime.dogovora.dto;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String login,
        String firstName,
        String secondName,
        String thirdName,
        String position,
        boolean isActive
) {
}
