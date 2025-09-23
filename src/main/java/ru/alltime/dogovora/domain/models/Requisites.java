package ru.alltime.dogovora.domain.models;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Requisites {
    private String ogrnOgrnip;          // yes
    private String inn;                 // yes
    private String kpp;                 // no
    private String legalAddress;        // no
    private String actualAddress;       // no
    private String currentAccount;      // no
    private String correspondentAccount;// no
}