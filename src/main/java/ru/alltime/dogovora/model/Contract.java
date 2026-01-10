package ru.alltime.dogovora.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "contract")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String contractNum;

    @Column(nullable = false)
    private boolean isActive;

    private LocalDate contractDateFrom;

    private LocalDate contractDateTo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client; //изменено тк hibernate ругается и не отображает uuid-mapping

    @ElementCollection
    @CollectionTable(
            name = "managerslist",
            joinColumns = @JoinColumn(name = "contractid")
    )
    @Column(name = "userid", nullable = false)
    private List<UUID> managerIdList; // todo по документации List<String> и managerId в виду массива! (на здоровье!)

    private String descriptionText;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    private List<HistoryRecord> historyList;

    @Enumerated(EnumType.STRING)
    private ContractStatus currentStatus;
}
