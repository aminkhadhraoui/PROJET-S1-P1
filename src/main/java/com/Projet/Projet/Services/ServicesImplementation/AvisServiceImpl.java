package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.Avis;
import com.Projet.Projet.Entities.Client;
import com.Projet.Projet.Entities.Formation;
import com.Projet.Projet.Repositories.AvisRepository;
import com.Projet.Projet.Services.AvisService;
import com.Projet.Projet.Services.ClientService;
import com.Projet.Projet.Services.FormationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AvisServiceImpl implements AvisService {
    private AvisRepository avisRepository;
    private FormationService formationService;
    private ClientService clientService;

    @Override
    public Avis addAvis(Avis avis) {
        avis.setDate(LocalDate.now());
        return avisRepository.save(avis);
    }

    @Override
    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }

    @Override
    public Avis getAvisById(Long avisId) {
        return avisRepository.findById(avisId).orElseThrow(
                () -> new NoSuchElementException("Aucun avis avec ID: " + avisId));
    }

    @Override
    public Avis updateAvis(Avis avis) {
        if (!avisRepository.existsById(avis.getId())){
            throw new NoSuchElementException("Aucun avis avec ID: " + avis.getId());
        }
        return avisRepository.save(avis);
    }

    @Override
    public void deleteAvis(Long avisId) {
        avisRepository.deleteById(avisId);
    }

    @Override
    public Avis addAvisToFormation(Long avisId, Long formationId) {
        Avis avis = getAvisById(avisId);
        Formation formation = formationService.getFormationById(formationId);
        avis.setFormation(formation);
        updateAvis(avis);
        formation.getAvis().add(avis);
        formationService.updateFormation(formation);
        return avis;
    }

    @Override
    public Avis addAvisToClient(Long avisId, Long clientId) {
        Avis avis = getAvisById(avisId);
        Client client = clientService.getClientById(clientId);
        avis.setClient(client);
        updateAvis(avis);
        client.getAvis().add(avis);
        clientService.updateClient(client);
        return avis;
    }
}
