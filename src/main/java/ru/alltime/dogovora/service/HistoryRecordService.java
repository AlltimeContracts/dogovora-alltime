package ru.alltime.dogovora.service;

import ru.alltime.dogovora.dto.HistoryRecordRequestDTO;
import ru.alltime.dogovora.dto.HistoryRecordResponseDTO;
import ru.alltime.dogovora.model.HistoryRecord;

import java.util.List;
import java.util.UUID;

public interface HistoryRecordService {

    List<HistoryRecordResponseDTO> findAllRecords();

    HistoryRecordResponseDTO findHistoryRecordById(UUID uuid);

    HistoryRecordResponseDTO createHistoryRecord(HistoryRecordRequestDTO historyRecordRequestDTO);

    HistoryRecordResponseDTO updateHistoryRecord(HistoryRecordRequestDTO historyRecordRequestDTO);

    void deleteHistoryRecordById(UUID uuid);


}
