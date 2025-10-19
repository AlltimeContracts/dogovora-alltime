package ru.alltime.dogovora.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.alltime.dogovora.dto.ClientDetailsRequestDTO;
import ru.alltime.dogovora.dto.ClientDetailsResponseDTO;
import ru.alltime.dogovora.model.ClientDetails;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ClientDetailsMapper {

    /**
     * DTO-запрос -> сущность.
     * Используется при создании новых реквизитов.
     * ID игнорируем — генерируется БД.
     */
    @Mapping(target = "id", ignore = true)
    ClientDetails toEntity(ClientDetailsRequestDTO dto);

    /**
     * Сущность -> DTO-ответ.
     * Используется при возврате данных наружу.
     */
    ClientDetailsResponseDTO toResponseDto(ClientDetails entity);

    /**
     * Частичное обновление существующей сущности из DTO-запроса.
     * null-поля в DTO НЕ затирают существующие значения.
     * Удобно для PATCH/частичного PUT.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ClientDetailsRequestDTO dto, @MappingTarget ClientDetails entity);

    /**
     * Маппинг списков (напр., для выдачи коллекций).
     */
    List<ClientDetailsResponseDTO> toResponseDtoList(List<ClientDetails> entities);
}