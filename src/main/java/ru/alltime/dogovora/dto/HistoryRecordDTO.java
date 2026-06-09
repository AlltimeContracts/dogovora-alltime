package ru.alltime.dogovora.dto;

import ru.alltime.dogovora.model.ContractStatus;

import java.util.UUID;

public record HistoryRecordDTO(
        UUID id,
        UUID userId,
        ContractStatus statusBefore,
        ContractStatus statusAfter,
        UUID contractBeforeId,
        UUID contractAfterId
) {
}
