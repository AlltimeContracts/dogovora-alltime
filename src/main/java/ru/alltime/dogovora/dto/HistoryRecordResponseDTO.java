package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotNull;
import ru.alltime.dogovora.model.ContractStatus;

import java.util.UUID;

public record HistoryRecordResponseDTO(
        UUID id,
        @NotNull UUID userId,
        @NotNull ContractStatus statusBefore,
        @NotNull ContractStatus statusAfter,
        @NotNull UUID contractBeforeId,
        @NotNull UUID contractAfterId
) {
}
