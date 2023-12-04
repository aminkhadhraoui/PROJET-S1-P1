package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.Formation;

import java.util.List;

public interface FormationService {

    Formation addFormation(Formation formation);
    List<Formation> getAllFormations();
    Formation getFormationById(Long formationId);
    Formation updateFormation(Formation formation);
    void deleteFormation(Long formationId);
    Formation addSeanceToFormation(Long seanceId, Long formationId);
    List<Formation> getFormationByTitre(String titre);
    List<Formation> getFormationByPrixBetween(double prix1, double prix2);
    List<Formation> getFormationsByCategorie_Id(Long categorieId);
    List<Formation> getFormationsByPrixBetweenAndCategorie_Id(double prix1, double prix2, Long categorieId);
    List<Formation> getFormationByTag(String tag);
    List<Formation> getFormationsByCentreFormation_Id(Long centerId);
    List<Formation> getFormationsByTagNameOrTitle(String param);
    Formation removeTagFromFormation(Long tagId, Long formationId);
    List<Formation> getFormationsByCategorie_Nom(String nom);
    List<Formation> findFirst10ByOrderByIdDesc();
    List<Formation> findFirst10ByCategorie_NomOrderByIdDesc(String categoryName);
    List<Formation> getAllCoursesForValidateAbonnement();

    Formation addFormateurToFormation(Long formateurId, Long formationId);
    Formation addOffreToFormation(Long oid, Long formationId);

    int getFormaationsNumber();
}
