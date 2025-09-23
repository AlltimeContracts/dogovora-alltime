package ru.alltime.dogovora.infrastructure.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.alltime.dogovora.domain.models.Contract;
import ru.alltime.dogovora.domain.models.HistoryRec;
import ru.alltime.dogovora.infrastructure.models.ContractEntity;
import ru.alltime.dogovora.infrastructure.models.HistoryRecEntity;

import java.util.List;

final class ContractMapper {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static Contract toDomain(ContractEntity e, List<HistoryRecEntity> history) {
        if (e == null) return null;
        Contract c = Contract.builder()
                .id(e.getId())
                .contractNum(e.getContractNum())
                .active(e.isActive())
                .fileList(e.getFileList())
                .contractDateFrom(e.getContractDateFrom())
                .contractDateTo(e.getContractDateTo())
                .clientId(e.getClientId())
                .managerId(e.getManagerId())
                .descriptionText(e.getDescriptionText())
                .currentStatus(e.getCurrentStatus())
                .build();
        if (history != null) {
            c.setHistoryList(history.stream().map(ContractMapper::histToDomain).toList());
        }
        return c;
    }

    static ContractEntity toEntity(Contract d) {
        if (d == null) return null;
        return ContractEntity.builder()
                .id(d.getId())
                .contractNum(d.getContractNum())
                .active(d.isActive())
                .fileList(d.getFileList())
                .contractDateFrom(d.getContractDateFrom())
                .contractDateTo(d.getContractDateTo())
                .clientId(d.getClientId())
                .managerId(d.getManagerId())
                .descriptionText(d.getDescriptionText())
                .currentStatus(d.getCurrentStatus())
                .build();
    }

    static HistoryRec histToDomain(HistoryRecEntity he) {
        HistoryRec hr = HistoryRec.builder()
                .timestamp(he.getTimestamp())
                .userId(he.getUserId())
                .statusBefore(he.getStatusBefore())
                .statusAfter(he.getStatusAfter())
                .contractBefore(fromJson(he.getContractBeforeJson()))
                .contractAfter(fromJson(he.getContractAfterJson()))
                .build();
        return hr;
    }

    static HistoryRecEntity histToEntity(String contractId, HistoryRec hr) {
        return HistoryRecEntity.builder()
                .contractId(contractId)
                .timestamp(hr.getTimestamp())
                .userId(hr.getUserId())
                .statusBefore(hr.getStatusBefore())
                .statusAfter(hr.getStatusAfter())
                .contractBeforeJson(toJson(hr.getContractBefore()))
                .contractAfterJson(toJson(hr.getContractAfter()))
                .build();
    }

    private static String toJson(Contract c) {
        if (c == null) return null;
        try { return MAPPER.writeValueAsString(c); }
        catch (JsonProcessingException e) { throw new RuntimeException(e); }
    }

    private static Contract fromJson(String json) {
        if (json == null || json.isBlank()) return null;
        try { return MAPPER.readValue(json, Contract.class); }
        catch (JsonProcessingException e) { throw new RuntimeException(e); }
    }

    private ContractMapper() {}
}
