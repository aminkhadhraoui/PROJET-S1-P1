package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.Categorie;
import com.Projet.Projet.Entities.Formation;
import com.Projet.Projet.Repositories.CategorieRepository;
import com.Projet.Projet.Repositories.ClientRepository;
import com.Projet.Projet.Services.CategorieService;
import com.Projet.Projet.Services.FormationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService {
    private CategorieRepository categorieRepository;
    private FormationService formationService;
    private ClientRepository clientRepository;

    @Override
    public Categorie addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie getCategorieById(Long categorieId) {
        return categorieRepository.findById(categorieId).orElseThrow(
                () -> new NoSuchElementException("Acune categorie avec ID: "+categorieId));
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public void deleteCategorie(Long categorieId) {
        List<Formation> formations = getCategorieById(categorieId).getFormations();
        for (Formation formation : formations) {
            clientRepository.findClientsByFormations_Id(formation.getId()).forEach(client -> client.getFormations().remove(formation));
        }
        categorieRepository.deleteById(categorieId);
    }

    @Override
    public Categorie updateCategorie(Categorie categorie) {
        if (!categorieRepository.existsById(categorie.getId())){
            throw new NoSuchElementException("Aucune categorie avec ID: "+categorie.getId());
        }
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie addFormationToCategorie(Long formationId, Long categorieId) {
        Categorie categorie = getCategorieById(categorieId);
        Formation formation = formationService.getFormationById(formationId);
        categorie.getFormations().add(formation);
        updateCategorie(categorie);
        formation.setCategorie(categorie);
        formationService.updateFormation(formation);
        return categorie;
    }

    @Override
    public Categorie getCategorieByNom(String nom) {
        return categorieRepository.getCategorieByNom(nom);
    }

    @Override
    public Categorie getCategorieByFormations_Id(Long id) {
        return categorieRepository.getCategorieByFormations_Id(id);
    }

    @Override
    public int getCategoriesNb() {
        return categorieRepository.getCategoriesNumber();
    }

    @Override
    public List<Categorie> getAllCategoriesContainsTrainings() {
        return categorieRepository.getNotNullCategories();
    public List<Map<String, Float>> getPercentageOfCoursesInEachCategory() {
        return categorieRepository.getPercentageOfCoursesInEachCategory();
    }
}
