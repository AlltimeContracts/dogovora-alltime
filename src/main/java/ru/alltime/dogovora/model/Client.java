package ru.alltime.dogovora.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Enumerated(EnumType.STRING)
    private BusinessForm businessForm;

    @Column(nullable = false)
    private String fullName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ClientDetails clientDetails;


    private String contractList;

    @Column(nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Contract> contracts;

}