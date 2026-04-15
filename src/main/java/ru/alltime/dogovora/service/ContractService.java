package ru.alltime.dogovora.service;

import ru.alltime.dogovora.dto.ContractDTO;

import java.util.List;
import java.util.UUID;

public interface ContractService {

    List<ContractResponseDTO> findAllContracts();

    ContractResponseDTO findContractById(UUID uuid);

    List<ContractResponseDTO> findContractsByContractNum(String contractNum);

    List<ContractResponseDTO> findContractsByIsActive(boolean isActive);

    ContractResponseDTO createContract(ContractDTO contractRequestDTO);

    ContractResponseDTO updateContract(ContractDTO contractRequestDTO);

    void deleteContractById(UUID uuid);

    // Contract uploadContractFile(File contract);


}
