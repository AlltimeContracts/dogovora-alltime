package ru.alltime.dogovora.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.alltime.dogovora.model.ClientDetails;
import ru.alltime.dogovora.repository.ClientDetailsRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class ClientDetailsService {

    private ClientDetailsRepository clientDetailsRepository;

    public List<ClientDetails> findAllClientDetails() {
        return clientDetailsRepository.findAll();
    }

    public ClientDetails findClientDetailsByOgrnOgrnip(String ogrnOgrnip) {
        return clientDetailsRepository.findClientDetailsByOgrnOgrnip(ogrnOgrnip).orElseThrow(() -> new EntityNotFoundException());
    }

    public ClientDetails findClientDetailsByInn(String inn) {
        return clientDetailsRepository.findClientDetailsByInn(inn).orElseThrow(() -> new EntityNotFoundException());
    }

    public ClientDetails createClientDetails(ClientDetails clientDetails) {
        return clientDetailsRepository.save(clientDetails);
    }

    public ClientDetails updateClientDetails(ClientDetails clientDetails) {
        return clientDetailsRepository.save(clientDetails);
    }

    public ClientDetails findClientDetailsByKpp(String kpp) {
        return clientDetailsRepository.findClientDetailsByKpp(kpp).orElseThrow(() -> new EntityNotFoundException());
    }

    public ClientDetails findClientDetailsByLegalAddress(String legalAddress) {
        return clientDetailsRepository.findClientDetailsByLegalAddress(legalAddress).orElseThrow(() -> new EntityNotFoundException());
    }

    public ClientDetails findClientDetailsByActualAddress(String actualAddress) {
        return clientDetailsRepository.findClientDetailsByActualAddress(actualAddress).orElseThrow(() -> new EntityNotFoundException());
    }

    public ClientDetails findClientDetailsByCurrentAccount(String currentAccount) {
        return clientDetailsRepository.findClientDetailsByCurrentAccount(currentAccount).orElseThrow(() -> new EntityNotFoundException());
    }

    public ClientDetails findClientDetailsByCorrespondentAccount(String correspondentAccount) {
        return clientDetailsRepository.findClientDetailsByCorrespondentAccount(correspondentAccount).orElseThrow(() -> new EntityNotFoundException());
    }

    public void deleteClientDetailsByInn(String inn) {
        clientDetailsRepository.deleteClientDetailsByInn(inn);
    }
}
