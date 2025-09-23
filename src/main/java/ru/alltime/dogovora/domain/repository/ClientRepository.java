package ru.alltime.dogovora.domain.repository;

import ru.alltime.dogovora.domain.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClientRepository {
    Page<Client> findAll(String search, Pageable pageable);
    Optional<Client> findById(String id);
    Client save(Client client);
    void softDelete(String id); // isActive = false
}
