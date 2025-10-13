package ru.alltime.dogovora.service;

import ru.alltime.dogovora.model.Contract;

import java.util.List;
import java.util.UUID;

public interface ContractService {

    List<Contract> findAllContracts();

    Contract findContractById(UUID uuid);

    Contract findContractByContractNum(String contractNum);

    List<Contract> findContractsByIsActive(boolean isActive);

    Contract createContract(Contract contract);

    Contract updateContract(Contract contract);

    void deleteContractById(UUID uuid);

}
