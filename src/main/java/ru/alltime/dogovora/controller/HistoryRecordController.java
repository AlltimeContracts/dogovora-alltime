package ru.alltime.dogovora.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alltime.dogovora.dto.HistoryRecordResponseDTO;
import ru.alltime.dogovora.service.HistoryRecordService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/history")
public class HistoryRecordController {

    private HistoryRecordService historyRecordService;

   public ResponseEntity<List<HistoryRecordResponseDTO>> findAllRecords(){
        return  historyRecordService.findAllRecords();
    }



}
