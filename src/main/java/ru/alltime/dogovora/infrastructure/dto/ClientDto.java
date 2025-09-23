package ru.alltime.dogovora.infrastructure.dto;

import lombok.*;
import ru.alltime.dogovora.domain.models.OrganizationalAndLegalForm;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClientDto {
    public static record RequisitesDto(
            String ogrnOgrnip,
            String inn,
            String kpp,
            String legalAddress,
            String actualAddress,
            String currentAccount,
            String correspondentAccount
    ) {}

    private String id; // может быть null при создании
    private OrganizationalAndLegalForm organizationalAndLegalForm;
    private String fullName;
    private RequisitesDto requisits;
    private List<String> contractsList;
    private Boolean isActive;
}
