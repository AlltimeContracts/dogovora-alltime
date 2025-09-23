package ru.alltime.dogovora.infrastructure.dto;

import ru.alltime.dogovora.domain.models.Contract;
import ru.alltime.dogovora.domain.models.HistoryRec;

import java.util.List;
import java.util.stream.Collectors;

public final class ContractDtoMapper {

    public static ru.alltime.dogovora.domain.models.Contract toDomain(ContractDto d) {
        if (d == null) return null;
        return ru.alltime.dogovora.domain.models.Contract.builder()
                .id(d.getId())
                .contractNum(d.getContractNum())
                .active(d.getActive() == null ? true : d.getActive())
                .fileList(d.getFileList())
                .contractDateFrom(d.getContractDateFrom())
                .contractDateTo(d.getContractDateTo())
                .clientId(d.getClientId())
                .managerId(d.getManagerId())
                .descriptionText(d.getDescriptionText())
                .historyList(fromDtoList(d.getHistoryList()))
                .currentStatus(d.getCurrentStatus())
                .build();
    }

    public static ContractDto toDto(ru.alltime.dogovora.domain.models.Contract c) {
        if (c == null) return null;
        return ContractDto.builder()
                .id(c.getId())
                .contractNum(c.getContractNum())
                .active(c.isActive())
                .fileList(c.getFileList())
                .contractDateFrom(c.getContractDateFrom())
                .contractDateTo(c.getContractDateTo())
                .clientId(c.getClientId())
                .managerId(c.getManagerId())
                .descriptionText(c.getDescriptionText())
                .historyList(toDtoList(c.getHistoryList()))
                .currentStatus(c.getCurrentStatus())
                .build();
    }

    private static List<ContractDto.HistoryRecDto> toDtoList(List<HistoryRec> list) {
        if (list == null) return null;
        return list.stream().map(h -> {
            // h.getContractBefore/After → это ДОМАШНИЙ Contract
            ContractDto before = h.getContractBefore() == null ? null : toDto(h.getContractBefore());
            ContractDto after  = h.getContractAfter()  == null ? null : toDto(h.getContractAfter());
            if (before != null) before.setHistoryList(null);
            if (after  != null) after.setHistoryList(null);
            return ContractDto.HistoryRecDto.builder()
                    .timestamp(h.getTimestamp())
                    .userId(h.getUserId())
                    .statusBefore(h.getStatusBefore())
                    .statusAfter(h.getStatusAfter())
                    .contractBefore(before)
                    .contractAfter(after)
                    .build();
        }).collect(Collectors.<ContractDto.HistoryRecDto>toList());
    }

    private static List<HistoryRec> fromDtoList(List<ContractDto.HistoryRecDto> list) {
        if (list == null) return null;
        return list.stream().map(h ->
                HistoryRec.builder()
                        .timestamp(h.getTimestamp())
                        .userId(h.getUserId())
                        .statusBefore(h.getStatusBefore())
                        .statusAfter(h.getStatusAfter())
                        // h.getContractBefore/After → это ContractDto, конвертируем в доменный Contract
                        .contractBefore(h.getContractBefore() == null ? null : toDomain(h.getContractBefore()))
                        .contractAfter(h.getContractAfter() == null ? null : toDomain(h.getContractAfter()))
                        .build()
        ).collect(Collectors.<HistoryRec>toList());
    }

    private ContractDtoMapper() {}
}