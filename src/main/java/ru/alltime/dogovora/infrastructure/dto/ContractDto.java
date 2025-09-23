package ru.alltime.dogovora.infrastructure.dto;

import lombok.*;
import ru.alltime.dogovora.domain.models.ContractStatus;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ContractDto {

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class HistoryRecDto {
        private String timestamp;
        private String userId;
        private ContractStatus statusBefore;
        private ContractStatus statusAfter;
        private ContractDto contractBefore; // целый объект, можно без historyList чтобы не зациклить
        private ContractDto contractAfter;
    }

    private String id;
    private String contractNum;
    private Boolean active;                 // nullable в DTO
    private List<String> fileList;
    private String contractDateFrom;
    private String contractDateTo;
    private String clientId;
    private List<String> managerId;
    private String descriptionText;
    private List<HistoryRecDto> historyList;
    private ContractStatus currentStatus;
}
