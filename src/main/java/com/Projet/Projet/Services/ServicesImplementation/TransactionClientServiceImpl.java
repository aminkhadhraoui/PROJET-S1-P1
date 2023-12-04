package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.Client;
import com.Projet.Projet.Entities.Formation;
import com.Projet.Projet.Entities.TransactionClient;
import com.Projet.Projet.Repositories.TransactionClientRepository;
import com.Projet.Projet.Services.ClientService;
import com.Projet.Projet.Services.FormationService;
import com.Projet.Projet.Services.TransactionClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class TransactionClientServiceImpl implements TransactionClientService {
    private TransactionClientRepository transactionClientRepository;
    private ClientService clientService;
    private FormationService formationService;

    @Override
    public TransactionClient addTransactionClient(TransactionClient transactionClient) {
        transactionClient.setDate(LocalDate.now());
        transactionClient.setHeure(LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute());
        return transactionClientRepository.save(transactionClient);
    }

    @Override
    public List<TransactionClient> getAllTransactions() {
        return transactionClientRepository.findAll();
    }

    @Override
    public List<TransactionClient> getTransactionsByClientId(Long clientId) {
        return transactionClientRepository.findTransactionClientByClient_Id(clientId);
    }

    @Override
    public TransactionClient getTransactionClientById(Long transactionId) {
        return transactionClientRepository.findById(transactionId).orElseThrow(
                () -> new NoSuchElementException("Acune transaction client avec ID: " + transactionId)
        );
    }

    @Override
    public TransactionClient updateTransactionClient(TransactionClient transactionClient) {
        if (!transactionClientRepository.existsById(transactionClient.getId())) {
            throw new NoSuchElementException("Acune transaction client avec ID: " + transactionClient.getId());
        }
        return transactionClientRepository.save(transactionClient);
    }

    @Override
    public void deleteTransactionClient(Long transactionId) {
        transactionClientRepository.deleteById(transactionId);
    }

    @Override
    public TransactionClient addTransactionToClient(Long clientId, Long transactionId) {
        Client client = clientService.getClientById(clientId);
        TransactionClient transactionClient = getTransactionClientById(transactionId);
        transactionClient.setClient(client);
        updateTransactionClient(transactionClient);
        client.getTransactions().add(transactionClient);
        clientService.updateClient(client);
        return transactionClient;
    }

    @Override
    public TransactionClient addTransactionToFormation(Long formationId, Long transactionId) {
        Formation formation = formationService.getFormationById(formationId);
        TransactionClient transactionClient = getTransactionClientById(transactionId);
        transactionClient.setFormation(formation);
        updateTransactionClient(transactionClient);
        formation.getTransactionClients().add(transactionClient);
        formationService.updateFormation(formation);
        return transactionClient;
    }
    @Override
    public List<Object[]> getSumTransactionsClientPerCategory() {
        return transactionClientRepository.getSumTransactionsPerCategory();


    @Override
    public List<TransactionClient> getTransactionClientsByFormation_Id(Long formationId) {
        return transactionClientRepository.getTransactionClientsByFormation_Id(formationId);
    }
}
