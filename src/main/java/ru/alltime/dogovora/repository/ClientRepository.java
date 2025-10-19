package ru.alltime.dogovora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alltime.dogovora.model.BusinessForm;
import ru.alltime.dogovora.model.Client;
import ru.alltime.dogovora.model.ClientDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface ClientRepository extends JpaRepository<Client, UUID> {


    List<Client> findClientsByFullName(String fullName); // Метод для поиска клиента по имени

    Optional<Client> findClientByIsActive(boolean isActive); // Метод для фильтрации по активности

    List<Client> findClientsByBusinessForm(String businessForm);

    Optional<Client> findClientByClientDetails(ClientDetails clientDetails);

    Optional<Client> findClientByContractList(String contractList);

    void deleteClientById(UUID id);

}
