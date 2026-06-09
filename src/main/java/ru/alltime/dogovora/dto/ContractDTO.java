package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotBlank;
import ru.alltime.dogovora.model.ContractStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ContractDTO(
        UUID id,
        @NotBlank String contractNum,
        boolean isActive,
        LocalDateTime contractDateFrom,
        LocalDateTime contractDateTo,
        UUID clientId,
        List<UUID> managerList,
        String descriptionText,
        ContractStatus contractStatus
) {
}
