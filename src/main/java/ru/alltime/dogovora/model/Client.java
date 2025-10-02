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
    @Column(name = "business_form")
    private BusinessForm businessForm; // todo подумать над сокращённым названием

    @Column(nullable = false)
    private String fullName;

    @OneToOne
    private ClientDetails clientDetails;

    private String contractList;

    @Column(nullable = false)
    private boolean isActive;

}