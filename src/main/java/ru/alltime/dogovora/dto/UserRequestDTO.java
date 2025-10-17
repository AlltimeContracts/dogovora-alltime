package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotEmpty String firstName,
        @NotEmpty String secondName,
        String thirdName,
        String position,
        @Size(min = 5, max = 20) String login
) {
}
