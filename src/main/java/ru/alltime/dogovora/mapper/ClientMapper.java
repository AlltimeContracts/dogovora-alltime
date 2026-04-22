package ru.alltime.dogovora.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alltime.dogovora.dto.ClientDTO;
import ru.alltime.dogovora.model.Client;
import ru.alltime.dogovora.model.ClientDetails;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    public ClientDTO toDto(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .businessForm(client.getBusinessForm())
                .fullName(client.getFullName())
                .clientDetailsId(client.getClientDetails() != null ? client.getClientDetails().getId() : null)
                .contractList(client.getContractList())
                .isActive(client.isActive())
                .build();
    }

    public List<ClientDTO> toDto(List<Client> clients) {
        return clients.stream().map(this::toDto).toList();
    }

    public Client toEntity(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setBusinessForm(dto.getBusinessForm());
        client.setFullName(dto.getFullName());
        client.setContractList(dto.getContractList());
        client.setActive(dto.isActive());

        if (dto.getClientDetailsId() != null) {
            ClientDetails clientDetails = new ClientDetails();
            clientDetails.setId(dto.getClientDetailsId());
            client.setClientDetails(clientDetails);
        }

        return client;
    }
}
