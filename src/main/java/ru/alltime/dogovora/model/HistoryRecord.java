package ru.alltime.dogovora.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "history_record")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // eсли один пользователь может иметь несколько записей истории
    private User user; // todo связь с пользователем, инициировавшим изменения !! поменяли на user

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractStatus statusBefore;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractStatus statusAfter;

    @ManyToOne
    @JoinColumn(name = "contract_before_id", nullable = false)
    Contract contractBefore;

    @ManyToOne
    @JoinColumn(name = "contract_after_id", nullable = false)
    Contract contractAfter;
}
