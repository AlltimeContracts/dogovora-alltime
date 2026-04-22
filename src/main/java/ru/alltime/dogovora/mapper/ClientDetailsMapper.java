package ru.alltime.dogovora.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alltime.dogovora.dto.ClientDetailsDTO;
import ru.alltime.dogovora.model.ClientDetails;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientDetailsMapper {

    public ClientDetailsDTO toDto(ClientDetails clientDetails) {
        return ClientDetailsDTO.builder()
                .id(clientDetails.getId())
                .ogrnOgrnip(clientDetails.getOgrnOgrnip())
                .inn(clientDetails.getInn())
                .kpp(clientDetails.getKpp())
                .legalAddress(clientDetails.getLegalAddress())
                .actualAddress(clientDetails.getActualAddress())
                .currentAccount(clientDetails.getCurrentAccount())
                .correspondentAccount(clientDetails.getCorrespondentAccount())
                .build();
    }

    public List<ClientDetailsDTO> toDto(List<ClientDetails> clientDetailsList) {
        return clientDetailsList.stream().map(this::toDto).toList();
    }

    public ClientDetails toEntity(ClientDetailsDTO dto) {
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setId(dto.getId());
        clientDetails.setOgrnOgrnip(dto.getOgrnOgrnip());
        clientDetails.setInn(dto.getInn());
        clientDetails.setKpp(dto.getKpp());
        clientDetails.setLegalAddress(dto.getLegalAddress());
        clientDetails.setActualAddress(dto.getActualAddress());
        clientDetails.setCurrentAccount(dto.getCurrentAccount());
        clientDetails.setCorrespondentAccount(dto.getCorrespondentAccount());
        return clientDetails;
    }
}
