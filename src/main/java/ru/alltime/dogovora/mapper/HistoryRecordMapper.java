package ru.alltime.dogovora.mapper;


import lombok.*;
import org.springframework.stereotype.Component;
import ru.alltime.dogovora.dto.HistoryRecordDTO;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HistoryRecordMapper {

    public HistoryRecordDTO toDto(HistoryRecordEntity historyRecordEntity) {

        return HistoryRecordDTO.builder()
                .id(historyRecordEntity.getId().toString())
                .userId(historyRecordEntity.getUserId())
                .statusBefore(historyRecordEntity.getStatusBefore())
                .statusAfter(historyRecordEntity.getStatusAfter())
                .contractBeforeId(historyRecordEntity.getContractBeforeId())
                .contractAfterId(historyRecordEntity.getContractAfterId())
                .build();
    }
    public List<HistoryRecordDTO> toDto(List<HistoryRecordEntity> historyRecordEntities) {
            return historyRecordEntities.stream().map(this::toDto).toList();
    }
}
