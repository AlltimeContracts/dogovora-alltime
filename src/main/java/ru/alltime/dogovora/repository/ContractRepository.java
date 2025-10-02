package ru.alltime.dogovora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alltime.dogovora.model.Contract;
import ru.alltime.dogovora.model.ContractStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface ContractRepository extends JpaRepository<Contract, UUID> {

    List<Contract> findContractsByCurrentStatus(ContractStatus status);// Метод для фильтрации по статусу

    Optional<Contract> findContractByContractNum(String contractNum);

    List<Contract> findContractsByIsActive(boolean isActive);


}
