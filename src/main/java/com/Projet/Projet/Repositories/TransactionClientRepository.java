package com.Projet.Projet.Repositories;

import com.Projet.Projet.Entities.TransactionClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionClientRepository extends JpaRepository<TransactionClient,Long> {
    List<TransactionClient> findTransactionClientByClient_Id(Long clientId);
    List<TransactionClient> getTransactionClientsByFormation_Id(Long formationId);

    @Query("SELECT c.nom, SUM(t.valeur) FROM Categorie c JOIN c.formations f JOIN f.transactionClients t GROUP BY c.nom")
    List<Object[]> getSumTransactionsPerCategory();
    // @Query("SELECT SUM(t.valeur) FROM Categorie c, Formation f, TransactionClient t JOIN c.formations JOIN f.transactionClients")



}
