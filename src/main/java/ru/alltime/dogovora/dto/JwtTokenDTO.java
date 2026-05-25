package ru.alltime.dogovora.dto;

public record JwtTokenDTO(
        String accessToken
        //TODO также надо получать refreshToken
) {
}
