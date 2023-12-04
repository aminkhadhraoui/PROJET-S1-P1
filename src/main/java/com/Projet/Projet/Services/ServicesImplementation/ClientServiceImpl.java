package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Configuration.JwtService;
import com.Projet.Projet.Entities.Client;
import com.Projet.Projet.Entities.Formation;
import com.Projet.Projet.Enum.Role;
import com.Projet.Projet.Repositories.ClientRepository;
import com.Projet.Projet.Services.ClientService;
import com.Projet.Projet.Services.FormationService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private FormationService formationService;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    @Override
    public Client addClient(Client client) {
        client.setRole(Role.CANDIDAT);
        client.setMdp(passwordEncoder.encode(client.getMdp()));
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(
                () -> new NoSuchElementException("Aucun client avec ID: " + clientId));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(Client client) {
        if (!clientRepository.existsById(client.getId())){
            throw new NoSuchElementException("Aucun client avec ID: " + client.getId());
        }
        Client clientUpdated = getClientById(client.getId());
        clientUpdated.setNom(client.getNom());
        clientUpdated.setPrenom(client.getPrenom());
        clientUpdated.setTel(client.getTel());
        clientUpdated.setAdresse(client.getAdresse());
        clientUpdated.setDateNaissance(client.getDateNaissance());
        clientUpdated.setFonction(client.getFonction());
        clientUpdated.setEmail(clientUpdated.getEmail());
        clientUpdated.setImg(client.getImg());
        return clientRepository.save(clientUpdated);
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public Client addClientToFormation(Long clientId, Long formationId) {
        Client client = getClientById(clientId);
        Formation formation = formationService.getFormationById(formationId);
        client.getFormations().add(formation);
        updateClient(client);
        formation.getClients().add(client);
        formationService.updateFormation(formation);
        return client;
    }

    @Override
    public List<Client> getClientsByAccountNonLocked(boolean value) {
        return clientRepository.getClientsByAccountNonLocked(value);
    }

    @Override
    public int getClientsNumber() {
        return clientRepository.getClientsNumber();
    }
}
