package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.*;
import com.Projet.Projet.Repositories.ClientRepository;
import com.Projet.Projet.Repositories.FormationRepository;
import com.Projet.Projet.Repositories.TagRepository;
import com.Projet.Projet.Services.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class FormationServiceImpl implements FormationService {

    private FormationRepository formationRepository;
    //private FormationService formationService;
    private SeanceService seanceService;
    private ClientRepository clientRepository;
    private OffreService offreService;
    private TagRepository tagRepository;
    private FormateurService formateurService;
    private AbonnementService abonnementService;

    @Override
    public Formation addFormation(Formation formation) {
        return formationRepository.save(formation);
    }

    @Override
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    @Override
    public Formation getFormationById(Long formationId) {
        return formationRepository.findById(formationId).orElseThrow(
                () -> new NoSuchElementException("Aucune formation avec ID: " + formationId));
    }

    @Override
    @Transactional
    public Formation updateFormation(Formation formationUpdate) {
        if (!formationRepository.existsById(formationUpdate.getId())) {
            throw new NoSuchElementException("Aucune formation avec ID : " + formationUpdate.getId());
        }
        Formation formation = formationRepository.findById(formationUpdate.getId()).get();
        formation.setDateDebut(formationUpdate.getDateDebut());
        formation.setDateFin(formationUpdate.getDateFin());
        formation.setTitre(formationUpdate.getTitre());
        formation.setPrix(formationUpdate.getPrix());
        formation.setNbMaxCan(formationUpdate.getNbMaxCan());
        formation.setDescription(formationUpdate.getDescription());
        formation.setImg(formationUpdate.getImg());
        return formation;
    }

    @Override
    public void deleteFormation(Long formationId) {
        Formation formation = getFormationById(formationId);
        tagRepository.getTagsByFormations_Id(formationId).forEach(tag -> tag.getFormations().remove(formation));
        clientRepository.findClientsByFormations_Id(formationId).forEach(client -> client.getFormations().remove(formation));
        formationRepository.deleteById(formationId);
    }

    @Override
    public Formation addSeanceToFormation(Long seanceId, Long formationId) {
        Seance seance = seanceService.getSeanceById(seanceId);
        Formation formation = getFormationById(formationId);
        formation.getSeances().add(seance);
        formationRepository.save(formation);
        seance.setFormation(formation);
        seanceService.updateSeance(seance);
        return formation;
    }

    @Override
    public List<Formation> getFormationByTitre(String titre) {
        return formationRepository.getFormationByTitre(titre);
    }

    @Override
    public List<Formation> getFormationByPrixBetween(double prix1, double prix2) {
        return formationRepository.getFormationByPrixBetween(prix1, prix2);
    }

    @Override
    public List<Formation> getFormationsByCategorie_Id(Long categorieId) {
        return formationRepository.getFormationsByCategorie_Id(categorieId);
    }

    @Override
    public List<Formation> getFormationsByPrixBetweenAndCategorie_Id(double prix1, double prix2, Long categorieId) {
        return formationRepository.getFormationsByPrixBetweenAndCategorie_Id(prix1, prix2, categorieId);
    }

    @Override
    public List<Formation> getFormationByTag(String tag) {
        return formationRepository.getFormationByTags_Nom(tag);
    }

    @Override
    public List<Formation> getFormationsByCentreFormation_Id(Long centerId) {
        return formationRepository.getFormationsByCentreFormation_Id(centerId);
    }

    @Override
    public List<Formation> getFormationsByTagNameOrTitle(String param) {
        return formationRepository.findByTagNameOrTitle(param);
    }

    @Override
    public Formation removeTagFromFormation(Long tagId, Long formationId) {
        Formation formation = getFormationById(formationId);
        Tag tag = tagRepository.findById(tagId).orElse(null);
        formation.getTags().remove(tag);
        tag.getFormations().remove(formation);
        formationRepository.save(formation);
        tagRepository.save(tag);
        return formation;
    }

    @Override
    public List<Formation> getFormationsByCategorie_Nom(String nom) {
        return formationRepository.getFormationsByCategorie_Nom(nom);
    }

    @Override
    public List<Formation> findFirst10ByOrderByIdDesc() {
        return formationRepository.findFirst10ByOrderByIdDesc();
    }

    @Override
    public List<Formation> findFirst10ByCategorie_NomOrderByIdDesc(String categoryName) {
        return formationRepository.findFirst10ByCategorie_NomOrderByIdDesc(categoryName);
    }

    @Override
    public List<Formation> getAllCoursesForValidateAbonnement() {
        List<Formation> formations = getAllFormations();
        List<Formation> validateFormations = new ArrayList<>();
        for (Formation formation : formations) {
            Abonnement abonnement = abonnementService.findFirstByCentreFormation_IdOrderByIdDesc(formation.getCentreFormation().getId());
            if (abonnement != null){
                if (abonnement.getDateFin().isAfter(LocalDate.now()) || abonnement.getDateFin().isEqual(LocalDate.now())) {
                    validateFormations.add(formation);
                }
            }
        }
        return validateFormations;
    }

    @Override
    public Formation addFormateurToFormation(Long formateurId, Long formationId) {
        Formation formation = getFormationById(formationId);
        Formateur formateur = formateurService.getFormateurById(formateurId);

        formation.getFormateurs().add(formateur);

        updateFormation(formation);

        formateur.getFormations().add(formation);
        formateurService.updateFormateur(formateurId, formateur);

        return formation;
    }


    public Formation addOffreToFormation(Long oid, Long fid) {
        Offre offre = offreService.getOfferById(oid);
        Formation formation = getFormationById(fid);

        formation.getOffres().add(offre);
        updateFormation(formation);

        offre.setFormation(formation);

        return formation;
    }

    @Override
    public int getFormaationsNumber() {
        return formationRepository.getFormaationsNumber();
    }
}