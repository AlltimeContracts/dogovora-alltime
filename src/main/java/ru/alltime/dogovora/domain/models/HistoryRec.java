package ru.alltime.dogovora.domain.models;

import lombok.*;
import ru.alltime.dogovora.domain.models.Contract;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HistoryRec {
    private String timestamp;          // string(timestamp)
    private String userId;             // id User
    private ContractStatus statusBefore;
    private ContractStatus statusAfter;
    private Contract contractBefore;   // целый снапшот
    private Contract contractAfter;    // целый снапшот
}
