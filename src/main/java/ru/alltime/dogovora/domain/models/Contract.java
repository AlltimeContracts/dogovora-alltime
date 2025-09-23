package ru.alltime.dogovora.domain.models;

import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Contract {
    private String id;                     // yes
    private String contractNum;            // yes
    private boolean active;                // yes (признак не удален)
    private List<String> fileList;         // no (ссылки)
    private String contractDateFrom;       // no string(timestamp)
    private String contractDateTo;         // no string(timestamp)
    private String clientId;               // yes (Client id)
    private List<String> managerId;        // yes (User ids)
    private String descriptionText;        // no
    private List<HistoryRec> historyList;  // no
    private ContractStatus currentStatus;  // yes
}