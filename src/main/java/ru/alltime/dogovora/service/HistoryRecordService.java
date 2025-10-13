package ru.alltime.dogovora.service;

import ru.alltime.dogovora.model.HistoryRecord;

import java.util.List;
import java.util.UUID;

public interface HistoryRecordService {

    List<HistoryRecord> findAllRecords();

    HistoryRecord findHistoryRecordById(UUID uuid);

    HistoryRecord createHistoryRecord(HistoryRecord historyRecord);

    HistoryRecord updateHistoryRecord(HistoryRecord historyRecord);

    void deleteHistoryRecordById(UUID uuid);


}
