package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alltime.dogovora.model.HistoryRecord;
import ru.alltime.dogovora.repository.HistoryRecordRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j

public class HistoryRecordServiceImpl implements HistoryRecordService {

    private HistoryRecordRepository historyRecordRepository;


    @Override
    public List<HistoryRecord> findAllRecords() {
        return historyRecordRepository.findAll();
    }

    @Override
    public HistoryRecord findHistoryRecordById(UUID uuid) {
        return historyRecordRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("Record not found"));
    }

    @Override
    public HistoryRecord createHistoryRecord(HistoryRecord historyRecord) {
         historyRecordRepository.save(historyRecord);
         log.info("History record created: {}", historyRecord);
         return   historyRecord;
    }

    @Override
    public HistoryRecord updateHistoryRecord(HistoryRecord historyRecord) {
        historyRecordRepository.save(historyRecord);
        log.info("Update history record: {}", historyRecord);
        return historyRecord;
    }

    @Override
    public void deleteHistoryRecordById(UUID uuid) {
        historyRecordRepository.deleteById(uuid);
        log.info("Delete history record: {}", uuid);
    }
}
