package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.*;
import com.Projet.Projet.Enum.EtatDemandeInscription;
import com.Projet.Projet.Repositories.CentreFormationRepository;
import com.Projet.Projet.Services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CentreFormationImpl implements CentreFormationService {
    private CentreFormationRepository centreFormationRepository;
    private AbonnementService abonnementService;
    private OffreService offreService;
    private CategorieService categorieService;
    private FormationService formationService;

    @Override
    public CentreFormation addCentre(CentreFormation centreFormation) {
        centreFormation.setEtatDemandeInscription(EtatDemandeInscription.EN_ATTENTE);
        return centreFormationRepository.save(centreFormation);

    }

    @Override
    public List<CentreFormation> getAllCentreFormation() {
        return centreFormationRepository.findAll();
    }

    @Override
    public String deleteCentreFormation(final Long id) {
        CentreFormation centreFormation = centreFormationRepository.findById(id).orElse(null);
        if (centreFormation != null) {
            centreFormationRepository.delete(centreFormation);
            return "centre supprimé avec succé";
        } else
            return "centre n'existe pas";


    }

    @Override
    public CentreFormation getCentreFormationById(Long id) {
        return centreFormationRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
                "NO Centre PRESENT WITH ID = " + id));
    }

    @Override
    public CentreFormation updateCentreFormation(Long cid, CentreFormation centreFormation) {
        CentreFormation centreFormationDB = getCentreFormationById(cid);

        centreFormationDB.setNom(centreFormation.getNom());
        centreFormationDB.setAdresse(centreFormationDB.getAdresse());
        centreFormationDB.setRib(centreFormation.getRib());
        centreFormationDB.setTel(centreFormation.getTel());
        centreFormationDB.setEmail(centreFormation.getEmail());

        centreFormationRepository.save(centreFormationDB);

        return centreFormationDB;
    }

    @Override
    public CentreFormation addOffreToCentreFormation(Long cid, Long oid) {
        CentreFormation centreFormation = getCentreFormationById(cid);
        Offre offre = offreService.getOfferById(oid);

        centreFormation.getOffres().add(offre);
        updateCentreFormation(cid, centreFormation);

        offre.setCentreFormation(centreFormation);
        return centreFormation;
    }

    @Override
    public CentreFormation addAbonnementToCentreFormation(Long aid, Long cid) {
        CentreFormation centreFormation = getCentreFormationById(cid);
        Abonnement abonnement = abonnementService.getAbonnementById(aid);

        centreFormation.getAbonnements().add(abonnement);
        updateCentreFormation(cid, centreFormation);

        abonnement.setCentreFormation(centreFormation);
        abonnementService.editAbonnement(abonnement.getId(),abonnement);
        return centreFormation;

    }

    @Override
    public CentreFormation addCategorieToCentreFormation(Long categorieId, Long centreId) {
        CentreFormation centreFormation = getCentreFormationById(centreId);
        Categorie categorie = categorieService.getCategorieById(categorieId);
        centreFormation.getCategorie().add(categorie);
        updateCentreFormation(centreId, centreFormation);
        categorie.getCentreFormations().add(centreFormation);
        categorieService.updateCategorie(categorie);
        return centreFormation;
    }

    @Override
    public CentreFormation addFormationToCentreFormation(Long formationId, Long centreId) {
        CentreFormation centreFormation = getCentreFormationById(centreId);
        Formation formation = formationService.getFormationById(formationId);
        centreFormation.getFormations().add(formation);
        updateCentreFormation(centreId, centreFormation);
        formation.setCentreFormation(centreFormation);
        formationService.updateFormation(formation);
        return centreFormation;
    }

    @Override
    public List<CentreFormation> getAllByManagerId(Long managerId) {
        return centreFormationRepository.getCentreFormationsByManager_Id(managerId);
    }

    @Override
    public List<CentreFormation> getCentreFormationsByEtatDemandeInscription(EtatDemandeInscription etat) {
        return centreFormationRepository.getCentreFormationsByEtatDemandeInscription(etat);
    }

    @Override
    public CentreFormation changeEtatDemandeIscription(Long idCenter, EtatDemandeInscription etatDemandeInscription) {
        CentreFormation centreFormation = getCentreFormationById(idCenter);
        centreFormation.setEtatDemandeInscription(etatDemandeInscription);
        return centreFormationRepository.save(centreFormation);
    }

    @Override
    public CentreFormation getCentreFormationByFormations_Id(Long formationId) {
        return centreFormationRepository.findCentreFormationByFormations_Id(formationId);
    }


}
