package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ru.alltime.dogovora.model.ContractStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ContractDTO{
        private UUID id;
        @NotBlank private String contractNum;
        private boolean isActive;
        private LocalDate contractDateFrom;
        private LocalDate contractDateTo;
        private UUID clientId;
        private List<UUID> managerList;
        private String descriptionText;
        private ContractStatus contractStatus;
}
