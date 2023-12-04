package com.Projet.Projet.Repositories;

import com.Projet.Projet.Entities.DemandeInscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeInscriptionRepository extends JpaRepository<DemandeInscription,Long> {
}
