package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotBlank;
import ru.alltime.dogovora.model.ContractStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ContractDTO(
        UUID id,
        @NotBlank String contractNum,
        boolean isActive,
        LocalDate contractDateFrom,
        LocalDate contractDateTo,
        UUID clientId,
        List<UUID> managerList,
        String descriptionText,
        ContractStatus contractStatus
) {
}
