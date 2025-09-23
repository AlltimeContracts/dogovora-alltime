package ru.alltime.dogovora.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alltime.dogovora.infrastructure.models.HistoryRecEntity;

import java.util.List;

public interface JpaHistoryRepository extends JpaRepository<HistoryRecEntity, Long> {
    List<HistoryRecEntity> findByContractIdOrderByIdAsc(String contractId);
}
