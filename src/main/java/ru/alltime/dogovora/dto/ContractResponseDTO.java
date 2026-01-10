package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.*;
import ru.alltime.dogovora.model.ContractStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ContractResponseDTO(
        @Null UUID id,
        @NotBlank String contractNum,
        @NotNull Boolean isActive,
        @PastOrPresent LocalDate contractDateFrom,
        @Future LocalDate contractDateTo,
        @NotNull UUID clientId,
        @NotEmpty List<UUID> managerList,
        @Size(max = 700) String descriptionText,
        @NotNull ContractStatus contractStatus
) {
}
