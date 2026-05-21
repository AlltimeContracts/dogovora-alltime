package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alltime.dogovora.dto.HistoryRecordDTO;
import ru.alltime.dogovora.mapper.HistoryRecordMapper;
import ru.alltime.dogovora.repository.HistoryRecordRepository;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class HistoryRecordServiceImpl implements HistoryRecordService {

    private HistoryRecordRepository historyRecordRepository;
    private HistoryRecordMapper recordMapper;

    @Override
    public List<HistoryRecordDTO> findAllRecords() {
        return historyRecordRepository.findAll().stream().map(recordMapper::toDto).toList();
    }

    @Override
    public HistoryRecordDTO findHistoryRecordById(UUID uuid) {
        var record = historyRecordRepository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("Record not found"));
        return recordMapper.toDto(record);
    }

    @Override
    public HistoryRecordDTO createHistoryRecord(HistoryRecordDTO historyRecordRequestDTO) {
        var createdRecord = recordMapper.toEntity(historyRecordRequestDTO);
        historyRecordRepository.save(createdRecord);
        log.info("History record created: {}", createdRecord);
        return recordMapper.toDto(createdRecord);
    }

    @Override
    public HistoryRecordDTO updateHistoryRecord(HistoryRecordDTO historyRecordRequestDTO) {
        var updatableRecord = recordMapper.toEntity(historyRecordRequestDTO);
        historyRecordRepository.save(updatableRecord);
        log.info("Update history record: {}", updatableRecord);
        return recordMapper.toDto(updatableRecord);
    }

    @Override
    public void deleteHistoryRecordById(UUID uuid) {
        historyRecordRepository.deleteById(uuid);
        log.info("Delete history record: {}", uuid);
    }
}
