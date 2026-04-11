package ru.alltime.dogovora.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "contracts")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String contractNum; // todo По документации String, хочется Integer (бывают буквы, смирись Илья!)

    @Column(nullable = false)
    private boolean isActive;

    private LocalDate contractDateFrom;

    private LocalDate contractDateTo;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    private List<UUID> managerIdList; // todo по документации List<String> и managerId в виду массива! (на здоровье!)

    private String descriptionText;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    private List<HistoryRecord> historyList;

    private ContractStatus currentStatus;
}
