package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.Client;

import java.util.List;

public interface ClientService {

    Client addClient(Client client);
    Client getClientById(Long clientId);
    List<Client> getAllClients();
    Client updateClient(Client client);
    void deleteClient(Long clientId);
    Client addClientToFormation(Long clientId, Long formationId);
    List<Client> getClientsByAccountNonLocked(boolean value);

    int getClientsNumber();

}
