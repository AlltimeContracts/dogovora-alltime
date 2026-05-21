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
        return new ContractDTO(
                contract.getId(),
                contract.getContractNum(),
                contract.isActive(),
                contract.getContractDateFrom(),
                contract.getContractDateTo(),
                contract.getClient() != null ? contract.getClient().getId() : null,
                contract.getManagerIds(),
                contract.getDescriptionText(),
                contract.getCurrentStatus()
        );
    }

    public List<ContractDTO> toDto(List<Contract> contracts) {
        return contracts.stream().map(this::toDto).toList();
    }

    public Contract toEntity(ContractDTO dto) {
        Contract contract = new Contract();
        contract.setId(dto.id());
        contract.setContractNum(dto.contractNum());
        contract.setActive(dto.isActive());
        contract.setContractDateFrom(dto.contractDateFrom());
        contract.setContractDateTo(dto.contractDateTo());
        contract.setManagerIds(dto.managerList());
        contract.setDescriptionText(dto.descriptionText());
        contract.setCurrentStatus(dto.contractStatus());

        if (dto.clientId() != null) {
            Client client = new Client();
            client.setId(dto.clientId());
            contract.setClient(client);
        }

        return contract;
    }
}
