package ru.alltime.dogovora.service;

import ru.alltime.dogovora.dto.ClientResponseDTO;
import ru.alltime.dogovora.model.BusinessForm;
import ru.alltime.dogovora.model.Client;
import ru.alltime.dogovora.model.ClientDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {

    List<Client> findAllClients();

    List<ClientResponseDTO> findClientById(UUID id);

    List<ClientResponseDTO>  findClientsByFullName(String fullName);

    List<ClientResponseDTO>  findClientByBusinessForm(String businessForm);

    Client findClientByClientDetails(ClientDetails clientDetails);

    Client findClientByContractList(String contractList);

    Client findClientByIsActive(boolean isActive);

    Client createClient(Client client);

    Client updateClient(Client client);

    void deleteClientById(UUID id);

}
