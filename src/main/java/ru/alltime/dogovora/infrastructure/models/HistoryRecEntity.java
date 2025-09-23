package ru.alltime.dogovora.infrastructure.models;

import jakarta.persistence.*;
import lombok.*;
import ru.alltime.dogovora.domain.models.ContractStatus;

@Entity
@Table(name = "contract_history")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HistoryRecEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="contract_id", nullable = false)
    private String contractId;

    @Column(name="timestamp", nullable = false)
    private String timestamp; // string(timestamp)

    @Column(name="user_id", nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name="status_before", nullable = false)
    private ContractStatus statusBefore;

    @Enumerated(EnumType.STRING)
    @Column(name="status_after", nullable = false)
    private ContractStatus statusAfter;

    @Lob
    @Column(name="contract_before", columnDefinition = "text")
    private String contractBeforeJson; // snapshot as JSON

    @Lob
    @Column(name="contract_after", columnDefinition = "text")
    private String contractAfterJson;  // snapshot as JSON
}
