package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.TransactionClient;

import java.util.List;

public interface TransactionClientService {

    TransactionClient addTransactionClient(TransactionClient transactionClient);
    List<TransactionClient> getAllTransactions();
    List<TransactionClient> getTransactionsByClientId(Long clientId);
    TransactionClient getTransactionClientById(Long transactionId);
    TransactionClient updateTransactionClient(TransactionClient transactionClient);
    void deleteTransactionClient(Long transactionId);
    TransactionClient addTransactionToClient(Long clientId, Long transactionId);
    TransactionClient addTransactionToFormation(Long formationId, Long transactionId);

    List<Object[]> getSumTransactionsClientPerCategory();

    List<TransactionClient> getTransactionClientsByFormation_Id(Long formationId);

}
