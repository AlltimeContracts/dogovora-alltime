package ru.alltime.dogovora.service;

import ru.alltime.dogovora.model.ClientDetails;

import java.util.List;

public interface ClientDetailsService {

    List<ClientDetails> findAllClientDetails();

    ClientDetails findClientDetailsByOgrnOgrnip(String ogrnOgrnip);

    ClientDetails findClientDetailsByInn(String inn);

    ClientDetails createClientDetails(ClientDetails clientDetails);

    ClientDetails updateClientDetails(ClientDetails clientDetails);

    ClientDetails findClientDetailsByKpp(String kpp);

    ClientDetails findClientDetailsByLegalAddress(String legalAddress);

    ClientDetails findClientDetailsByActualAddress(String actualAddress);

    ClientDetails findClientDetailsByCurrentAccount(String currentAccount);

    ClientDetails findClientDetailsByCorrespondentAccount(String correspondentAccount);

    void deleteClientDetailsByInn(String inn);

}