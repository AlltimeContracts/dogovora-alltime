package ru.alltime.dogovora.mapper;


import org.mapstruct.*;
import ru.alltime.dogovora.dto.HistoryRecordRequestDTO;
import ru.alltime.dogovora.dto.HistoryRecordResponseDTO;
import ru.alltime.dogovora.model.Contract;
import ru.alltime.dogovora.model.HistoryRecord;
import ru.alltime.dogovora.model.User;

import java.util.List;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface HistoryRecordMapper {
    // ---- RequestDTO → Entity ----
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "contractBefore", source = "contractBeforeId")
    @Mapping(target = "contractAfter", source = "contractAfterId")
    HistoryRecord toEntity(HistoryRecordRequestDTO dto);

    // ---- Entity → ResponseDTO ----
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "contractBeforeId", source = "contractBefore.id")
    @Mapping(target = "contractAfterId", source = "contractAfter.id")
    HistoryRecordResponseDTO toResponseDTO(HistoryRecord entity);

    // ---- Маппинг списков ----
    List<HistoryRecordResponseDTO> toResponseDTOList(List<HistoryRecord> entities);

    // ---- Обновление существующей сущности из DTO (опционально) ----
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "user", source = "userId")
    @Mapping(target = "contractBefore", source = "contractBeforeId")
    @Mapping(target = "contractAfter", source = "contractAfterId")
    void updateEntityFromDto(HistoryRecordRequestDTO dto, @MappingTarget HistoryRecord entity);

    // ====== Кастомные методы UUID <-> Entity ======

    default User map(UUID id) {
        if (id == null) return null;
        User user = new User();
        user.setId(id);
        return user;
    }

    default Contract mapContract(UUID id) {
        if (id == null) return null;
        Contract contract = new Contract();
        contract.setId(id);
        return contract;
    }

    default UUID map(User user) {
        return user != null ? user.getId() : null;
    }

    default UUID map(Contract contract) {
        return contract != null ? contract.getId() : null;
    }
}
