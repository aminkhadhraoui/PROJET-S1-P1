package com.Projet.Projet.Controller;

import com.Projet.Projet.Configuration.ImageUpload;
import com.Projet.Projet.Entities.Formation;
import com.Projet.Projet.Services.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/formation")
public class FormationController {

    @Autowired
    private FormationService formationService;

    @GetMapping("")
    public List<Formation> getAllFormations() {
        return formationService.getAllFormations();
    }

    @GetMapping("/{id}")
    public Formation getFormationById(@PathVariable("id") Long formationId) {
        return formationService.getFormationById(formationId);
    }

    @PostMapping("/add")
    public Formation addFormation(@RequestPart("formation") Formation formation,
                                  @RequestPart("image") MultipartFile image) {
        Formation formation1 = formationService.addFormation(formation);
        String orgFileName = StringUtils.cleanPath(image.getOriginalFilename());
        String ext = orgFileName.substring(orgFileName.lastIndexOf("."));
        String fileName = "training-" + formation1.getId() + ext;
        String uploadDir = "../SkillUp-FE/src/assets/training-photos";
        ImageUpload.saveFile(uploadDir, fileName, image);
        formation1.setImg(fileName);
        return formationService.addFormation(formation1);
    }

    @PutMapping("/update")
    public Formation updateFormation(@RequestPart("formation") Formation formation,
                                     @RequestPart("image") MultipartFile image) {
        String orgFileName = StringUtils.cleanPath(image.getOriginalFilename());
        if (!orgFileName.equals("")){
            String ext = orgFileName.substring(orgFileName.lastIndexOf("."));
            String fileName = "training-" + formation.getId() + ext;
            String uploadDir = "../SkillUp-FE/src/assets/training-photos";
            ImageUpload.saveFile(uploadDir, fileName, image);
            formation.setImg(fileName);
        }
        return formationService.updateFormation(formation);
    }

    @GetMapping("/{idf}/tag/{idt}")
    public Formation removeTagFromFormation(@PathVariable("idt") Long tagId,@PathVariable("idf") Long formationId){
        return formationService.removeTagFromFormation(tagId, formationId);
    }

    @GetMapping("/center/{id}")
    public List<Formation> getFormationsByCentreFormation_Id(@PathVariable("id") Long centerId) {
        return formationService.getFormationsByCentreFormation_Id(centerId);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteFormation(@PathVariable("id") Long formationId) {
        formationService.deleteFormation(formationId);
    }

    @GetMapping("/seance/{sid}/formation/{fid}")
    public Formation addSeanceToFormation(@PathVariable("sid") Long seanceId, @PathVariable("fid") Long formationId) {
        return formationService.addSeanceToFormation(seanceId, formationId);
    }

    @GetMapping("/titre/{titre}")
    public List<Formation> getFormationByTitre(@PathVariable String titre) {
        return formationService.getFormationByTitre(titre);
    }

    @GetMapping("/categorie/{id}")
    public List<Formation> getFormationsByCategorie_Id(@PathVariable Long id) {
        return formationService.getFormationsByCategorie_Id(id);
    }

    @GetMapping("/prix/{p1}/{p2}/categorie/{id}")
    public List<Formation> getFormationsByPrixBetweenAndCategorie_Id(@PathVariable double p1, @PathVariable double p2, @PathVariable Long id) {
        return formationService.getFormationsByPrixBetweenAndCategorie_Id(p1, p2, id);
    }

    @GetMapping("/prix/{p1}/{p2}")
    public List<Formation> getFormationByPrixBetween(@PathVariable double p1, @PathVariable double p2) {
        return formationService.getFormationByPrixBetween(p1, p2);
    }

    @GetMapping("/tag/{tag}")
    public List<Formation> getFormationByTag(@PathVariable String tag) {
        return formationService.getFormationByTag(tag);
    }

    @GetMapping("/{fid}/offre/{oid}")
    public Formation addOffreToFormation(@PathVariable Long fid, @PathVariable Long oid) {
        return formationService.addOffreToFormation(oid, fid);
    }

    @GetMapping("/{fid}/formateur/{formateurId}")
    public Formation addFormateurToFormation(@PathVariable("formateurId") Long formateurId, @PathVariable("fid") Long formationId) {
        return formationService.addFormateurToFormation(formateurId, formationId);
    }

    @GetMapping("/find/{param}")
    public List<Formation> getFormationsByTagNameOrTitle(@PathVariable("param") String param) {
        return formationService.getFormationsByTagNameOrTitle(param.toUpperCase());
    }

    @GetMapping("/category/{name}")
    public List<Formation> getFormationsByCategoryName(@PathVariable("name") String name){
        return formationService.getFormationsByCategorie_Nom(name);
    }

    @GetMapping("/last/added")
    public List<Formation> findFirst10ByOrderByIdDesc(){
        return formationService.findFirst10ByOrderByIdDesc();
    }

    @GetMapping("/category/name/{name}")
    List<Formation> findFirst10ByCategorie_NomOrderByIdDesc(@PathVariable("name") String categoryName){
        return formationService.findFirst10ByCategorie_NomOrderByIdDesc(categoryName);
    }

    @GetMapping("/all/valide")
    public List<Formation> getAllCoursesForValidateAbonnement(){
        return formationService.getAllCoursesForValidateAbonnement();
    }

    @GetMapping("/stats/trainingsCount")
    public int getTrainingsCount(){
        return formationService.getFormaationsNumber();
    }

}
