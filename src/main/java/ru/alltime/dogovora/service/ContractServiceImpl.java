package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alltime.dogovora.dto.ContractRequestDTO;
import ru.alltime.dogovora.dto.ContractResponseDTO;
import ru.alltime.dogovora.mapper.ContractMapper;
import ru.alltime.dogovora.model.Contract;
import ru.alltime.dogovora.repository.ContractRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;
    private ContractMapper contractMapper;

    @Override
    public List<ContractResponseDTO> findAllContracts() {
        return contractRepository.findAll().stream()
                .map(contractMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ContractResponseDTO findContractById(UUID uuid) {
        var foundContract = contractRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException());
        return contractMapper.toResponseDTO(foundContract);
    }

    @Override
    public ContractResponseDTO findContractByContractNum(String contractNum) {
        var foundNum = contractRepository.findContractByContractNum(contractNum);
        return contractMapper.toResponseDTO(foundNum);
    }

    @Override
    public List<ContractResponseDTO> findContractsByIsActive(boolean isActive) {
        return contractRepository.findContractsByIsActive(isActive).stream()
                                                                         .map(contractMapper::toResponseDTO)
                                                                             .toList();
    }

    @Override
    public ContractResponseDTO createContract(ContractRequestDTO contractRequestDTO) {
        Contract createdContract = contractMapper.toEntity(contractRequestDTO);
        contractRepository.save(createdContract);
        log.info("Contract created: {}", createdContract);
        return contractMapper.toResponseDTO(createdContract);
    }

    @Override
    public ContractResponseDTO updateContract(ContractRequestDTO contractRequestDTO) {
        Contract updatedContract = contractMapper.toEntity(contractRequestDTO);
        contractRepository.save(updatedContract);
        log.info("Contract updated: {}", updatedContract);
        return contractMapper.toResponseDTO(updatedContract);
    }

    @Override
    public void deleteContractById(UUID uuid) {
        contractRepository.deleteById(uuid);
        log.info("Contract deleted: {}", uuid);

    }
}
