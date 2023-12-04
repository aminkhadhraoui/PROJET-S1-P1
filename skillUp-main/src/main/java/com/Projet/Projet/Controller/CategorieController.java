package com.Projet.Projet.Controller;

import com.Projet.Projet.Entities.Categorie;
import com.Projet.Projet.Services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categorie")
@CrossOrigin(origins = "*")
public class CategorieController {
    @Autowired
    private CategorieService categorieService;

    @GetMapping("")
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Categorie getCategorieById(@PathVariable("id") Long categorieId) {
        return categorieService.getCategorieById(categorieId);
    }

    @PostMapping("/add")
    public Categorie addCategorie(@RequestBody Categorie categorie) {
        return categorieService.addCategorie(categorie);
    }

    @PutMapping("/update")
    public Categorie updateCategorie(@RequestBody Categorie categorie) {
        return categorieService.updateCategorie(categorie);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategorie(@PathVariable("id") Long categorieId) {
        categorieService.deleteCategorie(categorieId);
    }

    @GetMapping("/formation/{fid}/categorie/{cid}")
    public Categorie addFormationToCategorie(@PathVariable("fid") Long formationId, @PathVariable("cid") Long categorieId) {
        return categorieService.addFormationToCategorie(formationId, categorieId);
    }

    @GetMapping("/nom/{nom}")
    public Categorie getCategorieByName(@PathVariable("nom") String nom) {
        return categorieService.getCategorieByNom(nom);
    }

    @GetMapping("/formation/{id}")
    public Categorie getCategorieByFormations_Id(@PathVariable("id") Long id) {
        return categorieService.getCategorieByFormations_Id(id);
    }


    @GetMapping("/stats/categories/number")
    public int getCategoriesNumber(){
        return categorieService.getCategoriesNb();
    }

    @GetMapping("/stats/categoriesContainsTrainings")
    List<Categorie> getAllCategoriesContainsTrainings(){
        return categorieService.getAllCategoriesContainsTrainings();
    }


    @GetMapping("/percentage/courses")
    public List<Map<String, Float>> getPercentageOfCoursesInEachCategory() {
        return categorieService.getPercentageOfCoursesInEachCategory();
    }

}
