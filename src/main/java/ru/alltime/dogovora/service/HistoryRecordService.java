package ru.alltime.dogovora.service;

import ru.alltime.dogovora.dto.HistoryRecordDTO;

import java.util.List;
import java.util.UUID;

public interface HistoryRecordService {

    List<HistoryRecordResponseDTO> findAllRecords();

    HistoryRecordResponseDTO findHistoryRecordById(UUID uuid);

    HistoryRecordResponseDTO createHistoryRecord(HistoryRecordDTO historyRecordRequestDTO);

    HistoryRecordResponseDTO updateHistoryRecord(HistoryRecordDTO historyRecordRequestDTO);

    void deleteHistoryRecordById(UUID uuid);


}
