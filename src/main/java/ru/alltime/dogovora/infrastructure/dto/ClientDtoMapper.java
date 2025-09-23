package ru.alltime.dogovora.infrastructure.dto;

import ru.alltime.dogovora.domain.models.Client;
import ru.alltime.dogovora.domain.models.Requisites;

public final class ClientDtoMapper {
    public static Client toDomain(ClientDto d) {
        return Client.builder()
                .id(d.getId())
                .organizationalAndLegalForm(d.getOrganizationalAndLegalForm())
                .fullName(d.getFullName())
                .requisits(d.getRequisits() == null ? null : Requisites.builder()
                        .ogrnOgrnip(d.getRequisits().ogrnOgrnip())
                        .inn(d.getRequisits().inn())
                        .kpp(d.getRequisits().kpp())
                        .legalAddress(d.getRequisits().legalAddress())
                        .actualAddress(d.getRequisits().actualAddress())
                        .currentAccount(d.getRequisits().currentAccount())
                        .correspondentAccount(d.getRequisits().correspondentAccount())
                        .build())
                .contractsList(d.getContractsList())
                .isActive(d.getIsActive() == null ? true : d.getIsActive())
                .build();
    }

    public static ClientDto toDto(Client c) {
        ClientDto.RequisitesDto rq = c.getRequisits() == null ? null :
                new ClientDto.RequisitesDto(
                        c.getRequisits().getOgrnOgrnip(),
                        c.getRequisits().getInn(),
                        c.getRequisits().getKpp(),
                        c.getRequisits().getLegalAddress(),
                        c.getRequisits().getActualAddress(),
                        c.getRequisits().getCurrentAccount(),
                        c.getRequisits().getCorrespondentAccount()
                );
        return ClientDto.builder()
                .id(c.getId())
                .organizationalAndLegalForm(c.getOrganizationalAndLegalForm())
                .fullName(c.getFullName())
                .requisits(rq)
                .contractsList(c.getContractsList())
                .isActive(c.isActive())
                .build();
    }

    private ClientDtoMapper() {}
}