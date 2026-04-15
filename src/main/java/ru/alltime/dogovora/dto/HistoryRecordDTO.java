package ru.alltime.dogovora.dto;

import lombok.*;
import ru.alltime.dogovora.model.ContractStatus;

import java.util.UUID;

@Data
@Builder
public class HistoryRecordDTO{
        private UUID id;
        private UUID userId;
        private ContractStatus statusBefore;
        private ContractStatus statusAfter;
        private UUID contractBeforeId;
        private UUID contractAfterId;
}
