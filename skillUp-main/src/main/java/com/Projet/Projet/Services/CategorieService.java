package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.Categorie;

import java.util.List;
import java.util.Map;

public interface CategorieService {

    Categorie addCategorie(Categorie categorie);
    Categorie getCategorieById(Long categorieId);
    List<Categorie> getAllCategories();
    void deleteCategorie(Long categorieId);
    Categorie updateCategorie(Categorie categorie);
    Categorie addFormationToCategorie(Long formationId, Long categorieId);
    Categorie getCategorieByNom(String nom);
    Categorie getCategorieByFormations_Id(Long id);
    int getCategoriesNb();
    List<Categorie> getAllCategoriesContainsTrainings();
    List<Map<String, Float>> getPercentageOfCoursesInEachCategory();

}
