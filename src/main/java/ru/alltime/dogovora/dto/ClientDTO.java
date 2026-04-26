package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ru.alltime.dogovora.model.BusinessForm;

import java.util.UUID;

@Data
@Builder
public class ClientDTO{
        private UUID id;
        @NotBlank private BusinessForm businessForm;
        @NotBlank private String fullName;
        private UUID clientDetailsId;
        private String contractList;
        private boolean isActive;
}