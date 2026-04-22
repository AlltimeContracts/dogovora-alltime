package ru.alltime.dogovora.service;

import ru.alltime.dogovora.dto.ContractDTO;

import java.util.List;
import java.util.UUID;

public interface ContractService {

    List<ContractDTO> findAllContracts();

    ContractDTO findContractById(UUID uuid);

    List<ContractDTO> findContractsByContractNum(String contractNum);

    List<ContractDTO> findContractsByIsActive(boolean isActive);

    ContractDTO createContract(ContractDTO contractRequestDTO);

    ContractDTO updateContract(ContractDTO contractRequestDTO);

    void deleteContractById(UUID uuid);
}
