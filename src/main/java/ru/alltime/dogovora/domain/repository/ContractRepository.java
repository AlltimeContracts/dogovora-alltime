package ru.alltime.dogovora.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.alltime.dogovora.domain.models.Contract;

import java.util.Optional;

public interface ContractRepository {
    Page<Contract> findAll(String search, Pageable pageable);
    Optional<Contract> findById(String id);
    Contract save(Contract contract);
    void softDelete(String id); // active=false
}
