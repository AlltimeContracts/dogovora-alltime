package ru.alltime.dogovora.mapper;

import org.mapstruct.*;
import ru.alltime.dogovora.dto.ContractRequestDTO;
import ru.alltime.dogovora.dto.ContractResponseDTO;
import ru.alltime.dogovora.model.Contract;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ContractMapper {


    // ---- RequestDTO → Entity ----
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currentStatus", source = "contractStatus")
    @Mapping(target = "managerIdList", source = "managerList")
    @Mapping(target = "historyList", ignore = true)
    Contract toEntity(ContractRequestDTO dto);

    // ---- Entity → ResponseDTO ----
    @Mapping(target = "contractStatus", source = "currentStatus")
    @Mapping(target = "managerList", source = "managerIdList")
    ContractResponseDTO toResponseDTO(Contract entity);

    // ---- Маппинг списков ----
    List<ContractResponseDTO> toResponseDTOList(List<Contract> entities);

    // ---- Обновление существующей сущности из DTO (опционально) ----
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currentStatus", source = "contractStatus")
    @Mapping(target = "managerIdList", source = "managerList")
    @Mapping(target = "historyList", ignore = true)
    void updateEntityFromDto(ContractRequestDTO dto, @MappingTarget Contract entity);
}
