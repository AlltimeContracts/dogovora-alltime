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
        return new ClientDTO(
                client.getId(),
                client.getBusinessForm(),
                client.getFullName(),
                client.getClientDetails() != null ? client.getClientDetails().getId() : null,
                client.getContractList(),
                client.isActive()
        );
    }

    public List<ClientDTO> toDto(List<Client> clients) {
        return clients.stream().map(this::toDto).toList();
    }

    public Client toEntity(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.id());
        client.setBusinessForm(dto.businessForm());
        client.setFullName(dto.fullName());
        client.setContractList(dto.contractList());
        client.setActive(dto.isActive());

        if (dto.clientDetailsId() != null) {
            ClientDetails clientDetails = new ClientDetails();
            clientDetails.setId(dto.clientDetailsId());
            client.setClientDetails(clientDetails);
        }

        return client;
    }
}
