package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.CentreFormation;
import com.Projet.Projet.Enum.EtatDemandeInscription;

import java.util.List;

public interface CentreFormationService {
    CentreFormation addCentre(CentreFormation centreFormation);
    List<CentreFormation> getAllCentreFormation();
    String deleteCentreFormation(Long id) ;
    CentreFormation getCentreFormationById(Long id);
    //CentreFormation updateCentreFormation(CentreFormation centreFormation);

    CentreFormation updateCentreFormation(Long cid, CentreFormation centreFormation);

    CentreFormation addOffreToCentreFormation(Long cid, Long oid);

    CentreFormation addAbonnementToCentreFormation(Long aid, Long cid);
    CentreFormation addCategorieToCentreFormation(Long categorieId, Long centreId);
    CentreFormation addFormationToCentreFormation(Long formationId, Long centreId);
    List<CentreFormation> getAllByManagerId(Long managerId);
    List<CentreFormation> getCentreFormationsByEtatDemandeInscription(EtatDemandeInscription etat);
    CentreFormation changeEtatDemandeIscription(Long idCenter, EtatDemandeInscription etatDemandeInscription);
    CentreFormation getCentreFormationByFormations_Id(Long formationId);

}
