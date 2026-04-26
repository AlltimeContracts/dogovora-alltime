package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Data
@Builder
public class ClientDetailsDTO {
        private UUID id;
        @NotBlank private String ogrnOgrnip;
        @NotBlank private String inn;
        private String kpp;
        private String legalAddress;
        private String actualAddress;
        private String currentAccount;
        private String correspondentAccount;
}