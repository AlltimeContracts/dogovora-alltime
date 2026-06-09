package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import ru.alltime.dogovora.model.BusinessForm;

import java.util.UUID;

public record ClientDTO(
        UUID id,
        @NonNull BusinessForm businessForm,
        @NotBlank String fullName,
        UUID clientDetailsId,
        String contractList,
        boolean isActive
) {
}
