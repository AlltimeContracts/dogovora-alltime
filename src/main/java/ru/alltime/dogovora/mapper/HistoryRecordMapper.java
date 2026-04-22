package ru.alltime.dogovora.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alltime.dogovora.dto.HistoryRecordDTO;
import ru.alltime.dogovora.model.Contract;
import ru.alltime.dogovora.model.HistoryRecord;
import ru.alltime.dogovora.model.User;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HistoryRecordMapper {

    public HistoryRecordDTO toDto(HistoryRecord historyRecord) {
        return HistoryRecordDTO.builder()
                .id(historyRecord.getId())
                .userId(historyRecord.getUser() != null ? historyRecord.getUser().getId() : null)
                .statusBefore(historyRecord.getStatusBefore())
                .statusAfter(historyRecord.getStatusAfter())
                .contractBeforeId(historyRecord.getContractBefore() != null ? historyRecord.getContractBefore().getId() : null)
                .contractAfterId(historyRecord.getContractAfter() != null ? historyRecord.getContractAfter().getId() : null)
                .build();
    }

    public List<HistoryRecordDTO> toDto(List<HistoryRecord> historyRecords) {
        return historyRecords.stream().map(this::toDto).toList();
    }

    public HistoryRecord toEntity(HistoryRecordDTO dto) {
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setId(dto.getId());
        historyRecord.setStatusBefore(dto.getStatusBefore());
        historyRecord.setStatusAfter(dto.getStatusAfter());

        if (dto.getUserId() != null) {
            User user = new User();
            user.setId(dto.getUserId());
            historyRecord.setUser(user);
        }

        if (dto.getContractBeforeId() != null) {
            Contract contractBefore = new Contract();
            contractBefore.setId(dto.getContractBeforeId());
            historyRecord.setContractBefore(contractBefore);
        }

        if (dto.getContractAfterId() != null) {
            Contract contractAfter = new Contract();
            contractAfter.setId(dto.getContractAfterId());
            historyRecord.setContractAfter(contractAfter);
        }

        return historyRecord;
    }
}
