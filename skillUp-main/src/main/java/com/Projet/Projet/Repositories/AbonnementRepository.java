package com.Projet.Projet.Repositories;

import com.Projet.Projet.Entities.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    Abonnement findFirst1ByCentreFormation_IdOrderByIdDesc(Long idCenter);

    @Query("SELECT MONTH(a.dateDebut) as month, COUNT(a.id) as count FROM Abonnement a " +
            "WHERE YEAR(a.dateDebut) = YEAR(CURRENT_DATE) " +
            "GROUP BY MONTH(a.dateDebut)")
    List<Map<String, Long>> findSubscriptionsByMonthForCurrentYear();

}
