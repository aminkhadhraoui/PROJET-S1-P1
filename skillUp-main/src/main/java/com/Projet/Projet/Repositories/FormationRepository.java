package com.Projet.Projet.Repositories;

import com.Projet.Projet.Entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormationRepository extends JpaRepository<Formation,Long> {
    List<Formation> getFormationByTitre(String titre);
    List<Formation> getFormationByPrixBetween(double prix1, double prix2);
    List<Formation> getFormationsByCategorie_Id(Long categorieId);
    List<Formation> getFormationsByPrixBetweenAndCategorie_Id(double prix1, double prix2, Long categorieId);
    List<Formation> getFormationByTags_Nom(String tag);
    List<Formation> getFormationsByCentreFormation_Id(Long centerId);
    @Query("SELECT f FROM Formation f LEFT JOIN f.tags t WHERE UPPER(f.titre) LIKE %:query% OR UPPER(t.nom) LIKE %:query%")
    List<Formation> findByTagNameOrTitle(@Param("query") String query);
    List<Formation> getFormationsByCategorie_Nom(String nom);
    List<Formation> findFirst10ByOrderByIdDesc();
    List<Formation> findFirst10ByCategorie_NomOrderByIdDesc(String CategoryName);

    @Query("SELECT count(*) FROM Formation")
    int getFormaationsNumber();
}
