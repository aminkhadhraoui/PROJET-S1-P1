package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.DemandeInscription;

import java.util.List;

public interface DemandeInscriptionService {

    DemandeInscription addDemande(DemandeInscription demandeInscription);
    DemandeInscription getDemandeById(Long demandeId);
    List<DemandeInscription> getAllDemandes();
    DemandeInscription updateDemande(DemandeInscription demandeInscription);
    void deleteDemande(Long demandeId);
    DemandeInscription addDemandeToClient(Long clientId, Long demandeId);
    DemandeInscription addDemandeToFormation(Long formationId, Long demandeId);
}
