package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alltime.dogovora.dto.ClientDTO;
import ru.alltime.dogovora.mapper.ClientMapper;
import ru.alltime.dogovora.model.Client;
import ru.alltime.dogovora.model.ClientDetails;
import ru.alltime.dogovora.repository.ClientRepository;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public ClientDTO findClientById(UUID id) {
        Client existingClient = clientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return clientMapper.toDto(existingClient);
    }

    @Override
    public List<ClientDTO> findClientsByFullName(String fullName) {
        List<Client> existingClients = clientRepository.findClientsByFullName(fullName);
        return existingClients.stream().map(clientMapper::toDto).toList();
    }

    @Override
    public List<ClientDTO> findClientsByBusinessForm(String businessForm) {
        List<Client> existingClients = clientRepository.findClientsByBusinessForm(businessForm);
        return existingClients.stream().map(clientMapper::toDto).toList();
    }

    @Override
    public ClientDTO findClientByClientDetails(ClientDetails clientDetails) {
        Client client = clientRepository.findClientByClientDetails(clientDetails).orElseThrow(EntityNotFoundException::new);
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDTO findClientByContractList(String contractList) {
        Client existingClient = clientRepository.findClientByContractList(contractList).orElseThrow(EntityNotFoundException::new);
        return clientMapper.toDto(existingClient);
    }

    @Override
    public List<ClientDTO> findClientsByIsActive(boolean isActive) {
        List<Client> activeClients = clientRepository.findClientsByIsActive(isActive);
        return activeClients.stream().map(clientMapper::toDto).toList();
    }

    @Override
    public ClientDTO createClient(ClientDTO clientRequestDTO) {
        Client client = clientRepository.save(clientMapper.toEntity(clientRequestDTO));
        ClientDTO responseDTO = clientMapper.toDto(client);
        log.info("Client saved: {}", responseDTO);
        return responseDTO;
    }

    @Override
    public void deleteClientById(UUID id) {
        clientRepository.deleteClientById(id);
        log.info("Client deleted: {}", id);
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientRequestDTO) {
        Client client = clientRepository.save(clientMapper.toEntity(clientRequestDTO));
        ClientDTO responseDTO = clientMapper.toDto(client);
        log.info("Updated client: {}", responseDTO);
        return responseDTO;
    }
}
