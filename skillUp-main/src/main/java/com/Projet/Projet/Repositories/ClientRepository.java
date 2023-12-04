package com.Projet.Projet.Repositories;

import com.Projet.Projet.Entities.Client;
import com.Projet.Projet.Entities.TransactionClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findClientsByFormations_Id(Long formationId);
    List<Client> getClientsByAccountNonLocked(boolean value);

    @Query("SELECT count(*) FROM Client")
    int getClientsNumber();

}
