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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currentStatus", source = "contractStatus")
    @Mapping(target = "managerIds", source = "managerList")
    @Mapping(target = "historyList", ignore = true)
    @Mapping(target = "active", source = "isActive")
    @Mapping(target = "contractNum", source = "contractNum")
    Contract toEntity(ContractRequestDTO dto);

    @Mapping(target = "contractStatus", source = "currentStatus")
    @Mapping(target = "managerList", source = "managerIds")
    @Mapping(target = "isActive", source = "active")
    ContractResponseDTO toResponseDTO(Contract entity);

    List<ContractResponseDTO> toResponseDTOList(List<Contract> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "currentStatus", source = "contractStatus")
    @Mapping(target = "managerIds", source = "managerList")
    @Mapping(target = "historyList", ignore = true)
    @Mapping(target = "active", source = "isActive")
    @Mapping(target = "contractNum", source = "contractNum")
    void updateEntityFromDto(ContractRequestDTO dto, @MappingTarget Contract entity);
}
