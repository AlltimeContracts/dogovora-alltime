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
        return new ClientDetailsDTO(
                clientDetails.getId(),
                clientDetails.getOgrnOgrnip(),
                clientDetails.getInn(),
                clientDetails.getKpp(),
                clientDetails.getLegalAddress(),
                clientDetails.getActualAddress(),
                clientDetails.getCurrentAccount(),
                clientDetails.getCorrespondentAccount()
        );
    }

    public List<ClientDetailsDTO> toDto(List<ClientDetails> clientDetailsList) {
        return clientDetailsList.stream().map(this::toDto).toList();
    }

    public ClientDetails toEntity(ClientDetailsDTO dto) {
        ClientDetails clientDetails = new ClientDetails();
        clientDetails.setId(dto.id());
        clientDetails.setOgrnOgrnip(dto.ogrnOgrnip());
        clientDetails.setInn(dto.inn());
        clientDetails.setKpp(dto.kpp());
        clientDetails.setLegalAddress(dto.legalAddress());
        clientDetails.setActualAddress(dto.actualAddress());
        clientDetails.setCurrentAccount(dto.currentAccount());
        clientDetails.setCorrespondentAccount(dto.correspondentAccount());
        return clientDetails;
    }
}
