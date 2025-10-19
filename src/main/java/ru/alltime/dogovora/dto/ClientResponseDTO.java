package ru.alltime.dogovora.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import ru.alltime.dogovora.model.BusinessForm;

import java.util.UUID;

public record ClientResponseDTO (
    @NotBlank UUID id,                    // Уникальный идентификатор клиента
    @NotBlank BusinessForm businessForm,  // Организационно-правовая форма
    @NotBlank String fullName,       // Полное наименование клиента
    @NotBlank UUID clientDetailsId,// ID связанных реквизитов (ClientDetails)
    @NotBlank String contractList,        // Список договоров (например, JSON или строка)
    @NotEmpty boolean isActive           // Активен ли клиент
) {
}