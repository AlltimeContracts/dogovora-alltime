package ru.alltime.dogovora.infrastructure.models;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RequisitesEmbeddable {
    private String ogrnOgrnip;
    private String inn;
    private String kpp;
    private String legalAddress;
    private String actualAddress;
    private String currentAccount;
    private String correspondentAccount;
}