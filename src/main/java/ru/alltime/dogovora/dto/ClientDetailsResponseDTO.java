package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ClientDetailsResponseDTO(
        @NotBlank UUID id,                   // Уникальный идентификатор реквизитов
        @NotBlank String ogrnOgrnip,         // ОГРН или ОГРНИП
        @NotBlank String inn,                // ИНН
        String kpp,                          // КПП
        String legalAddress,                 // Юридический адрес
        String actualAddress,                // Фактический адрес
        String currentAccount,               // Расчётный счёт
        String correspondentAccount          // Корреспондентский счёт
) { }