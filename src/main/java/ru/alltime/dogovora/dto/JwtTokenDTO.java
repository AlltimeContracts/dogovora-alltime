package ru.alltime.dogovora.dto;

public record JwtTokenDTO(
        String accessToken,
        String refreshToken //TODO реализовать в следующей задаче
) {
}
