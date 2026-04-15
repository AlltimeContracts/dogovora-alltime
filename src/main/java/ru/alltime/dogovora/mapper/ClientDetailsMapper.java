package ru.alltime.dogovora.mapper;

import lombok.*;
import org.springframework.stereotype.Component;
import ru.alltime.dogovora.dto.ClientDetailsDTO;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientDetailsMapper {

    public ClientDetailsDTO toDto(ClientDetailsEntity clientDetailsEntity){

        return ClientDetailsDTO.builder()
                .id(clientDetailsEntity.getId().toString())
                .ogrnOgrnip(clientDetailsEntity.getOgrnOgrnip())
                .inn(clientDetailsEntity.getInn())
                .kpp(clientDetailsEntity.getKpp())
                .legalAddress(clientDetailsEntity.getLegalAddress())
                .actualAddress(clientDetailsEntity.getActualAddress())
                .currentAccount(clientDetailsEntity.getCurrentAccount())
                .correspondentAccount(clientDetailsEntity.getCorrespondentAccount())
                .build();
    }
    public List<ClientDetailsDTO> toDto(List<ClientDetailsEntity> clientDetailsEntities){
        return clientDetailsEntities.stream().map(this::toDto).toList();
    }
}