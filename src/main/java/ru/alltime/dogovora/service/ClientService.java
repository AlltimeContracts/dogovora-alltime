package ru.alltime.dogovora.service;

import ru.alltime.dogovora.dto.ClientRequestDTO;
import ru.alltime.dogovora.dto.ClientResponseDTO;
import ru.alltime.dogovora.model.Client;
import ru.alltime.dogovora.model.ClientDetails;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    List<Client> findAllClients();

    ClientResponseDTO findClientById(UUID id);

    List<ClientResponseDTO>  findClientsByFullName(String fullName);

    List<ClientResponseDTO>  findClientsByBusinessForm(String businessForm);

    ClientResponseDTO findClientByClientDetails(ClientDetails clientDetails);

    ClientResponseDTO findClientByContractList(String contractList);

    List<ClientResponseDTO> findClientsByIsActive(boolean isActive);

    ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO);

    ClientResponseDTO updateClient(ClientRequestDTO clientRequestDTO);

    void deleteClientById(UUID id);

}
