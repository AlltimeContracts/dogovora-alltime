package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alltime.dogovora.dto.ContractDTO;
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
    public List<ContractDTO> findAllContracts() {
        return contractRepository.findAll().stream().map(contractMapper::toDto).toList();
    }

    @Override
    public ContractDTO findContractById(UUID uuid) {
        Contract foundContract = contractRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        return contractMapper.toDto(foundContract);
    }

    @Override
    public List<ContractDTO> findContractsByContractNum(String contractNum) {
        List<Contract> contractsByContractNum = contractRepository.findContractsByContractNum(contractNum);
        return contractsByContractNum.stream().map(contractMapper::toDto).toList();
    }

    @Override
    public List<ContractDTO> findContractsByIsActive(boolean isActive) {
        return contractRepository.findContractsByIsActive(isActive).stream().map(contractMapper::toDto).toList();
    }

    @Override
    public ContractDTO createContract(ContractDTO contractRequestDTO) {
        Contract createdContract = contractMapper.toEntity(contractRequestDTO);
        contractRepository.save(createdContract);
        log.info("Contract created: {}", createdContract);
        return contractMapper.toDto(createdContract);
    }

    @Override
    public ContractDTO updateContract(ContractDTO contractRequestDTO) {
        Contract updatedContract = contractMapper.toEntity(contractRequestDTO);
        contractRepository.save(updatedContract);
        log.info("Contract updated: {}", updatedContract);
        return contractMapper.toDto(updatedContract);
    }

    @Override
    public void deleteContractById(UUID uuid) {
        contractRepository.deleteById(uuid);
        log.info("Contract deleted: {}", uuid);
    }
}
