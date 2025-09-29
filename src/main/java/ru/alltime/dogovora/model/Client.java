package ru.alltime.dogovora.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "organozation_and_legal_form")
    private OrganizationalAndLegalForm organizationalAndLegalForm;

    @Column(nullable = false)
    private String fullName;

    @OneToOne
    private Requisits requisits;

    private String contractList;

    @Column(nullable = false)
    private boolean isActive;

}