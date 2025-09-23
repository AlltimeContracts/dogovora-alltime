package ru.alltime.dogovora.infrastructure.repository;

import ru.alltime.dogovora.domain.models.Client;
import ru.alltime.dogovora.domain.models.Requisites;
import ru.alltime.dogovora.infrastructure.models.ClientEntity;
import ru.alltime.dogovora.infrastructure.models.RequisitesEmbeddable;

final class ClientMapper {
    static Client toDomain(ClientEntity e) {
        if (e == null) return null;
        return Client.builder()
                .id(e.getId())
                .organizationalAndLegalForm(e.getOrganizationalAndLegalForm())
                .fullName(e.getFullName())
                .requisits(toDomain(e.getRequisits()))
                .contractsList(e.getContractsList())
                .isActive(e.isActive())
                .build();
    }

    static ClientEntity toEntity(Client d) {
        if (d == null) return null;
        return ClientEntity.builder()
                .id(d.getId())
                .organizationalAndLegalForm(d.getOrganizationalAndLegalForm())
                .fullName(d.getFullName())
                .requisits(toEmb(d.getRequisits()))
                .contractsList(d.getContractsList())
                .isActive(d.isActive())
                .build();
    }

    private static Requisites toDomain(RequisitesEmbeddable r) {
        if (r == null) return null;
        return Requisites.builder()
                .ogrnOgrnip(r.getOgrnOgrnip())
                .inn(r.getInn())
                .kpp(r.getKpp())
                .legalAddress(r.getLegalAddress())
                .actualAddress(r.getActualAddress())
                .currentAccount(r.getCurrentAccount())
                .correspondentAccount(r.getCorrespondentAccount())
                .build();
    }

    private static RequisitesEmbeddable toEmb(Requisites r) {
        if (r == null) return null;
        return RequisitesEmbeddable.builder()
                .ogrnOgrnip(r.getOgrnOgrnip())
                .inn(r.getInn())
                .kpp(r.getKpp())
                .legalAddress(r.getLegalAddress())
                .actualAddress(r.getActualAddress())
                .currentAccount(r.getCurrentAccount())
                .correspondentAccount(r.getCorrespondentAccount())
                .build();
    }

    private ClientMapper() {}
}