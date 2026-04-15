package ru.alltime.dogovora.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
public class ClientDetailsDTO {
        private UUID id;
        private String ogrnOgrnip;         // ОГРН или ОГРНИП (обязательно)
        private String inn;                // ИНН (обязательно)
        private String kpp;                          // КПП (опционально)
        private String legalAddress;                 // Юридический адрес
        private String actualAddress;                // Фактический адрес
        private String currentAccount;               // Расчётный счёт
        private String correspondentAccount;          // Корреспондентский счёт
}