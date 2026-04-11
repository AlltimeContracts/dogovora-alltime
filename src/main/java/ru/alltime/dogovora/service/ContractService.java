package ru.alltime.dogovora.service;

import ru.alltime.dogovora.dto.ContractRequestDTO;
import ru.alltime.dogovora.dto.ContractResponseDTO;
import ru.alltime.dogovora.model.Contract;

import java.io.File;
import java.util.List;
import java.util.UUID;

public interface ContractService {

    List<ContractResponseDTO> findAllContracts();

    ContractResponseDTO findContractById(UUID uuid);

    List<ContractResponseDTO> findContractsByContractNum(String contractNum);

    List<ContractResponseDTO> findContractsByIsActive(boolean isActive);

    ContractResponseDTO createContract(ContractRequestDTO contractRequestDTO);

    ContractResponseDTO updateContract(ContractRequestDTO contractRequestDTO);

    void deleteContractById(UUID uuid);

    // Contract uploadContractFile(File contract);


}
