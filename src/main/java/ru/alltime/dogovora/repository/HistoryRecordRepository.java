package ru.alltime.dogovora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alltime.dogovora.model.ContractStatus;
import ru.alltime.dogovora.model.HistoryRecord;

import java.util.List;
import java.util.UUID;

@Repository

public interface HistoryRecordRepository extends JpaRepository<HistoryRecord, UUID> {

    List<HistoryRecord> findByUserId(UUID userId); // Найти все записи истории по пользователю

    List<HistoryRecord> findByContractBeforeId(UUID contractId); // Найти все записи истории по договору "до"

    List<HistoryRecord> findByContractAfterId(UUID contractId); // Найти все записи истории по договору "после"

    List<HistoryRecord> findByStatusBeforeIs(ContractStatus status); // Найти все записи истории по конкретному статусу "до"

    List<HistoryRecord> findByStatusAfterIs(ContractStatus status);// Найти все записи истории по конкретному статусу "после"

    //List<HistoryRecord> findHistoryRecordsByContractNum(String contractNum);

}
