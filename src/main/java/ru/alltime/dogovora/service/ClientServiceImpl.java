package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alltime.dogovora.dto.ClientRequestDTO;
import ru.alltime.dogovora.dto.ClientResponseDTO;
import ru.alltime.dogovora.mapper.ClientMapper;
import ru.alltime.dogovora.model.BusinessForm;
import ru.alltime.dogovora.model.Client;
import ru.alltime.dogovora.model.ClientDetails;
import ru.alltime.dogovora.repository.ClientRepository;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j

public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public ClientResponseDTO findClientById(UUID id) {
        Client existingClient = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return clientMapper.toResponseDto(existingClient);
    }

    @Override
    public List<ClientResponseDTO> findClientsByFullName(String fullName) {
        List<Client> existingClients = clientRepository.findClientsByFullName(fullName);
        return existingClients.stream()
                .map(clientMapper::toResponseDto)
                .toList();

    }

    @Override
    public List<ClientResponseDTO> findClientsByBusinessForm(String businessForm) {
        List<Client> existingClients = clientRepository.findClientsByBusinessForm(businessForm);
        return existingClients.stream()
                .map(clientMapper::toResponseDto)
                .toList();

    }

    @Override
    public ClientResponseDTO findClientByClientDetails(ClientDetails clientDetails) {
        Client client = clientRepository.findClientByClientDetails(clientDetails).orElseThrow(() -> new EntityNotFoundException());
        return clientMapper.toResponseDto(client);
    }

    @Override
    public ClientResponseDTO findClientByContractList(String contractList) {  // todo ждем ответа
        Client existingClient = clientRepository.findClientByContractList(contractList).orElseThrow(() -> new EntityNotFoundException());
        return clientMapper.toResponseDto(existingClient);
    }

    @Override
    public List<ClientResponseDTO> findClientsByIsActive(boolean isActive) {
        List<Client> activeClients = clientRepository.findClientsByIsActive(isActive);
        return activeClients.stream()
                .map(clientMapper::toResponseDto)
                .toList();

    }

    @Override
    public ClientRequestDTO createClient(ClientRequestDTO clientRequestDTO) {
        clientRepository.save(clientMapper.toEntity(clientRequestDTO));
        log.info("Client saved: {}", clientRequestDTO);
        return clientRequestDTO;
    }

    @Override
    public void deleteClientById(UUID id) {   //todo какая логика удаления?
        clientRepository.deleteClientById(id);
        log.info("Client deleted: {}", id);
    }

    @Override
    public ClientRequestDTO updateClient(ClientRequestDTO clientRequestDTO) {
        clientRepository.save(clientMapper.toEntity(clientRequestDTO));
        log.info("Update client: {}", clientRequestDTO);
        return clientRequestDTO;
    }

}
