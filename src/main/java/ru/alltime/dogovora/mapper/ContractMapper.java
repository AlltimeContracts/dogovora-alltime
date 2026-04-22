package ru.alltime.dogovora.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alltime.dogovora.dto.ContractDTO;
import ru.alltime.dogovora.model.Client;
import ru.alltime.dogovora.model.Contract;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContractMapper {

    public ContractDTO toDto(Contract contract) {
        return ContractDTO.builder()
                .id(contract.getId())
                .contractNum(contract.getContractNum())
                .isActive(contract.isActive())
                .contractDateFrom(contract.getContractDateFrom())
                .contractDateTo(contract.getContractDateTo())
                .clientId(contract.getClient() != null ? contract.getClient().getId() : null)
                .managerList(contract.getManagerIdList())
                .descriptionText(contract.getDescriptionText())
                .contractStatus(contract.getCurrentStatus())
                .build();
    }

    public List<ContractDTO> toDto(List<Contract> contracts) {
        return contracts.stream().map(this::toDto).toList();
    }

    public Contract toEntity(ContractDTO dto) {
        Contract contract = new Contract();
        contract.setId(dto.getId());
        contract.setContractNum(dto.getContractNum());
        contract.setActive(dto.isActive());
        contract.setContractDateFrom(dto.getContractDateFrom());
        contract.setContractDateTo(dto.getContractDateTo());
        contract.setManagerIdList(dto.getManagerList());
        contract.setDescriptionText(dto.getDescriptionText());
        contract.setCurrentStatus(dto.getContractStatus());

        if (dto.getClientId() != null) {
            Client client = new Client();
            client.setId(dto.getClientId());
            contract.setClient(client);
        }

        return contract;
    }
}
