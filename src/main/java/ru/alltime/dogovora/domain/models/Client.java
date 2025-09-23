package ru.alltime.dogovora.domain.models;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Client {
    private String id;                                   // yes
    private OrganizationalAndLegalForm organizationalAndLegalForm; // no
    private String fullName;                             // yes
    private Requisites requisits;                        // no (object)
    private List<String> contractsList;                  // no (ids of Contract)
    private boolean isActive;                            // yes
}