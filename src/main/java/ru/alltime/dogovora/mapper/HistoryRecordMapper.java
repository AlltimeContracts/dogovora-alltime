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
        return new HistoryRecordDTO(
                historyRecord.getId(),
                historyRecord.getUser() != null ? historyRecord.getUser().getId() : null,
                historyRecord.getStatusBefore(),
                historyRecord.getStatusAfter(),
                historyRecord.getContractBefore() != null ? historyRecord.getContractBefore().getId() : null,
                historyRecord.getContractAfter() != null ? historyRecord.getContractAfter().getId() : null
        );
    }

    public List<HistoryRecordDTO> toDto(List<HistoryRecord> historyRecords) {
        return historyRecords.stream().map(this::toDto).toList();
    }

    public HistoryRecord toEntity(HistoryRecordDTO dto) {
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setId(dto.id());
        historyRecord.setStatusBefore(dto.statusBefore());
        historyRecord.setStatusAfter(dto.statusAfter());

        if (dto.userId() != null) {
            User user = new User();
            user.setId(dto.userId());
            historyRecord.setUser(user);
        }

        if (dto.contractBeforeId() != null) {
            Contract contractBefore = new Contract();
            contractBefore.setId(dto.contractBeforeId());
            historyRecord.setContractBefore(contractBefore);
        }

        if (dto.contractAfterId() != null) {
            Contract contractAfter = new Contract();
            contractAfter.setId(dto.contractAfterId());
            historyRecord.setContractAfter(contractAfter);
        }

        return historyRecord;
    }
}
