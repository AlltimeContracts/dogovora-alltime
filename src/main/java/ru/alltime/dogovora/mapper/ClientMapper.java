package ru.alltime.dogovora.mapper;

import org.mapstruct.*;
import ru.alltime.dogovora.dto.*;
import ru.alltime.dogovora.model.*;

import java.util.UUID;

/**
 * Маппер для преобразования между сущностью Client и DTO.
 * Использует вложенный маппер ClientDetailsMapper для реквизитов.
 */
@Mapper(
        componentModel = "spring",
        uses = { ClientDetailsMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ClientMapper {

    /**
     * Преобразование DTO-запроса → сущность.
     * Используется при создании нового клиента.
     */
    @Mapping(target = "id", ignore = true) // ID генерируется в БД
    @Mapping(target = "clientDetails", source = "clientDetailsId", qualifiedByName = "mapClientDetailsId")
    Client toEntity(ClientRequestDTO dto);

    /**
     * Преобразование сущности → DTO-ответ.
     * Используется при возврате данных пользователю.
     */
    @Mapping(target = "clientDetailsId", source = "clientDetails.id")
    ClientResponseDTO toResponseDto(Client client);

    /**
     * Дополнительный метод для конверсии UUID → ClientDetails.
     * Используется только при маппинге ClientRequestDTO → Client.
     */
    @Named("mapClientDetailsId")
    default ClientDetails mapClientDetailsId(UUID id) {
        if (id == null) return null;
        ClientDetails details = new ClientDetails();
        details.setId(id);
        return details;
    }
}