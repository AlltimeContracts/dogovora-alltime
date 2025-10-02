package ru.alltime.dogovora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alltime.dogovora.model.ClientDetails;

import java.util.Optional;

@Repository

public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Long> {

    Optional<ClientDetails> findClientDetailsByOgrnOgrnip(String ogrnOgrnip); // Поиск по ОГРН/ОГРНИП

    Optional<ClientDetails> findClientDetailsByInn(String inn); // Поиск по ИНН

    Optional<ClientDetails> findClientDetailsByKpp(String kpp); // Поиск по КПП

    Optional<ClientDetails> findClientDetailsByLegalAddress(String legalAddress); // Поиск по юридическому адресу

    Optional<ClientDetails> findClientDetailsByActualAddress(String actualAddress); // Поиск по фактическому адресу

    Optional<ClientDetails> findClientDetailsByCorrespondentAccount(String correspondentAccount); // Поиск по КПП

    Optional<ClientDetails> findClientDetailsByCurrentAccount(String currentAccount); // Поиск по КПП

    void deleteClientDetailsByInn(String inn);

}
