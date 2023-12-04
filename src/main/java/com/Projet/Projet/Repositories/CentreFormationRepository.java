package com.Projet.Projet.Repositories;

import com.Projet.Projet.Entities.CentreFormation;
import com.Projet.Projet.Enum.EtatDemandeInscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CentreFormationRepository extends JpaRepository<CentreFormation, Long> {
    List<CentreFormation> getCentreFormationsByManager_Id(Long managerId);
    List<CentreFormation> getCentreFormationsByEtatDemandeInscription(EtatDemandeInscription etat);
    CentreFormation findCentreFormationByFormations_Id(Long formationId);

}