package ru.alltime.dogovora.mapper;

import lombok.*;
import org.springframework.stereotype.Component;
import ru.alltime.dogovora.dto.ClientDTO;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    public ClientDTO toDto(ClientEntity clientEntity) {

        return ClientDTO.builder()
                .id(clientEntity.getId().toString())
                .businessForm(clientEntity.getBusinessForm())
                .fullName(clientEntity.getFullName())
                .clientDetailsId(clientEntity.getClientDetailsId())
                .contractList(clientEntity.getContractList())
                .isActive(clientEntity.getIsActive())
                .build();
    }
    public List<ClientDTO> toDto(List<ClientEntity> clientEntities){
        return clientEntities.stream().map(this::toDto).toList();
    }
}