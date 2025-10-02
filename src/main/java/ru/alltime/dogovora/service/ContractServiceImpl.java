package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alltime.dogovora.model.Contract;
import ru.alltime.dogovora.repository.ContractRepository;

import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class ContractServiceImpl implements ContractService {

    private ContractRepository contractRepository;


    @Override
    public List<Contract> findAllContracts() {
        return contractRepository.findAll();
    }

    @Override
    public Contract findContractById(UUID uuid) {
        return contractRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException());
    }


    @Override
    public List<Contract> findContractsByIsActive(boolean isActive) {
        return contractRepository.findContractsByIsActive(isActive);
    }

    @Override
    public Contract createContract(Contract contract) {
        contractRepository.save(contract);
        log.info("Contract created: {}", contract);
        return contract;
    }

    @Override
    public Contract updateContract(Contract contract) {
        contractRepository.save(contract);
        log.info("Contract updated: {}", contract);
        return contract;
    }

    @Override
    public void deleteContractById(UUID uuid) {
        contractRepository.deleteById(uuid);
        log.info("Contract deleted: {}", uuid);

    }
}
