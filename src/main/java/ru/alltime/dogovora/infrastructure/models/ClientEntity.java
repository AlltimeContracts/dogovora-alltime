package ru.alltime.dogovora.infrastructure.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import ru.alltime.dogovora.domain.models.OrganizationalAndLegalForm;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClientEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id; // используем строковый UUID, генерируй в сервисе или тут

    @Enumerated(EnumType.STRING)
    @Column(name = "organizational_legal_form")
    private OrganizationalAndLegalForm organizationalAndLegalForm;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Embedded
    private RequisitesEmbeddable requisits;

    // Храним список id договоров в таблице-связке (ElementCollection)
    @ElementCollection
    @CollectionTable(name = "client_contracts", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "contract_id")
    private List<String> contractsList = new ArrayList<>();

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}