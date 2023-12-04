package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.Client;
import com.Projet.Projet.Entities.DemandeInscription;
import com.Projet.Projet.Entities.Formation;
import com.Projet.Projet.Repositories.DemandeInscriptionRepository;
import com.Projet.Projet.Services.ClientService;
import com.Projet.Projet.Services.DemandeInscriptionService;
import com.Projet.Projet.Services.FormationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class DemandeInscriptionServiceImpl implements DemandeInscriptionService {
    private DemandeInscriptionRepository demandeInscriptionRepository;
    private ClientService clientService;
    private FormationService formationService;

    @Override
    public DemandeInscription addDemande(DemandeInscription demandeInscription) {
        demandeInscription.setDate(LocalDate.now());
        return demandeInscriptionRepository.save(demandeInscription);
    }

    @Override
    public DemandeInscription getDemandeById(Long demandeId) {
        return demandeInscriptionRepository.findById(demandeId).orElseThrow(
                () -> new NoSuchElementException("Aucune demande avec ID: "+demandeId));
    }

    @Override
    public List<DemandeInscription> getAllDemandes() {
        return demandeInscriptionRepository.findAll();
    }

    @Override
    public DemandeInscription updateDemande(DemandeInscription demandeInscription) {
        if (!demandeInscriptionRepository.existsById(demandeInscription.getId())){
            throw new NoSuchElementException("Aucune demande avec ID: "+demandeInscription.getId());
        }
        return demandeInscriptionRepository.save(demandeInscription);
    }

    @Override
    public void deleteDemande(Long demandeId) {
        demandeInscriptionRepository.deleteById(demandeId);
    }

    @Override
    public DemandeInscription addDemandeToClient(Long clientId, Long demandeId) {
        DemandeInscription demandeInscription = getDemandeById(demandeId);
        Client client = clientService.getClientById(clientId);
        demandeInscription.setClient(client);
        updateDemande(demandeInscription);
        client.getDemandeInscriptions().add(demandeInscription);
        clientService.updateClient(client);
        return demandeInscription;
    }

    @Override
    public DemandeInscription addDemandeToFormation(Long formationId, Long demandeId) {
        DemandeInscription demandeInscription = getDemandeById(demandeId);
        Formation formation = formationService.getFormationById(formationId);
        demandeInscription.setFormation(formation);
        updateDemande(demandeInscription);
        formation.getDemandes().add(demandeInscription);
        formationService.updateFormation(formation);
        return demandeInscription;
    }
}
