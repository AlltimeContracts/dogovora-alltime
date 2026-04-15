package ru.alltime.dogovora.mapper;

import lombok.*;
import org.springframework.stereotype.Component;
import ru.alltime.dogovora.dto.ContractDTO;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContractMapper {

    public ContractDTO toDto(ContractEntity contractEntity) {

        return ContractDTO.builder()
                .id(contractEntity.getId().toString())
                .contractNum(contractEntity.getContractNum())
                .isActive(contractEntity.getIsActive())
                .contractDateFrom(contractEntity.getContractDateFrom())
                .contractDateTo(contractEntity.getContractDateTo())
                .clientId(contractEntity.getClientId())
                .managerList(contractEntity.getManagerList())
                .descriptionText(contractEntity.getDescriptionText())
                .contractStatus(contractEntity.getContractStatus())
                .build();
    }
    public List<ContractDTO> toDto(List<ContractEntity> contractEntities){
        return contractEntities.stream().map(this::toDto).toList();
    }

}
