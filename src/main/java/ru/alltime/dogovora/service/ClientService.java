package ru.alltime.dogovora.service;

import ru.alltime.dogovora.dto.ClientDTO;
import ru.alltime.dogovora.model.Client;
import ru.alltime.dogovora.model.ClientDetails;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    List<Client> findAllClients();

    ClientDTO findClientById(UUID id);

    List<ClientDTO> findClientsByFullName(String fullName);

    List<ClientDTO> findClientsByBusinessForm(String businessForm);

    ClientDTO findClientByClientDetails(ClientDetails clientDetails);

    ClientDTO findClientByContractList(String contractList);

    List<ClientDTO> findClientsByIsActive(boolean isActive);

    ClientDTO createClient(ClientDTO clientRequestDTO);

    ClientDTO updateClient(ClientDTO clientRequestDTO);

    void deleteClientById(UUID id);
}
