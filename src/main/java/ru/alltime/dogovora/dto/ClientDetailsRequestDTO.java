package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record ClientDetailsRequestDTO(
        @NotBlank String ogrnOgrnip,         // ОГРН или ОГРНИП (обязательно)
        @NotBlank String inn,                // ИНН (обязательно)
        String kpp,                          // КПП (опционально)
        String legalAddress,                 // Юридический адрес
        String actualAddress,                // Фактический адрес
        String currentAccount,               // Расчётный счёт
        String correspondentAccount          // Корреспондентский счёт
) {
}