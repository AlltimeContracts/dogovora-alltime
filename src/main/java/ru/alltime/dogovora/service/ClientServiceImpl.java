package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client findClientByFullName(String fullName) {
        return clientRepository.findClientByFullName(fullName).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Client findClientByBusinessForm(BusinessForm businessForm) {
        return clientRepository.findClientByBusinessForm(businessForm).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Client findClientByClientDetails(ClientDetails clientDetails) {
        return clientRepository.findClientByClientDetails(clientDetails).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Client findClientByContractList(String contractList) {
        return clientRepository.findClientByContractList(contractList).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Client findClientByIsActive(boolean isActive) {
        return clientRepository.findClientByIsActive(isActive).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Client createClient(Client client) {
        clientRepository.save(client);
        log.info("Client saved: {}", client);
        return client;
    }

    @Override
    public void deleteClientById(UUID id) {
        clientRepository.deleteClientById(id);
        log.info("Client deleted: {}", id);
    }

    @Override
    public Client updateClient(Client client) {
        clientRepository.save(client);
        log.info("Update client: {}", client);
        return client;
    }

}
