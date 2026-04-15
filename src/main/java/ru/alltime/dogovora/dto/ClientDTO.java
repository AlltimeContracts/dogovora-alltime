package ru.alltime.dogovora.dto;

import lombok.*;
import ru.alltime.dogovora.model.BusinessForm;

import java.util.UUID;

@Data
@Builder
public class ClientDTO{
        private UUID id;
        private BusinessForm businessForm;  // Организационно-правовая форма
        private String fullName;       // Полное наименование клиента
        private UUID clientDetailsId;   // ID связанных реквизитов (ClientDetails)
        private String contractList;        // Список договоров (например, JSON или строка)
        private boolean isActive;          // Активен ли клиент
}