package ru.alltime.dogovora.service;

import ru.alltime.dogovora.dto.HistoryRecordDTO;

import java.util.List;
import java.util.UUID;

public interface HistoryRecordService {

    List<HistoryRecordDTO> findAllRecords();

    HistoryRecordDTO findHistoryRecordById(UUID uuid);

    HistoryRecordDTO createHistoryRecord(HistoryRecordDTO historyRecordRequestDTO);

    HistoryRecordDTO updateHistoryRecord(HistoryRecordDTO historyRecordRequestDTO);

    void deleteHistoryRecordById(UUID uuid);
}
