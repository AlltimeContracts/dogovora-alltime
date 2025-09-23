package ru.alltime.dogovora.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.alltime.dogovora.infrastructure.models.ContractEntity;

public interface JpaContractRepository extends JpaRepository<ContractEntity, String>, JpaSpecificationExecutor<ContractEntity> {
}
