package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ClientDetailsDTO(
        UUID id,
        @NotBlank String ogrnOgrnip,
        @NotBlank String inn,
        String kpp,
        String legalAddress,
        String actualAddress,
        String currentAccount,
        String correspondentAccount
) {
}
