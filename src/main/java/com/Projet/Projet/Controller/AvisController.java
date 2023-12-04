package com.Projet.Projet.Controller;

import com.Projet.Projet.Entities.Avis;
import com.Projet.Projet.Services.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
@CrossOrigin(origins = "*")
public class AvisController {

    @Autowired
    private AvisService avisService;

    @GetMapping("")
    public List<Avis> getAllAvis(){
        return avisService.getAllAvis();
    }
    @GetMapping("/{id}")
    public Avis getAvisById(@PathVariable("id") Long avisId){
        return avisService.getAvisById(avisId);
    }
    @PostMapping("/add")
    public Avis addAvis(@RequestBody Avis avis){
        return avisService.addAvis(avis);
    }
    @PutMapping("/update")
    public Avis updateAvis(@RequestBody Avis avis){
        return avisService.updateAvis(avis);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteAvis(@PathVariable("id") Long avisId){
        avisService.deleteAvis(avisId);
    }

    @GetMapping("/formation/{fid}/avis/{aid}")
    public Avis addAvisToFormation(@PathVariable("aid") Long avisId, @PathVariable("fid") Long formationId){
        return avisService.addAvisToFormation(avisId,formationId);
    }
    @GetMapping("/client/{cid}/avis/{aid}")
    public Avis addAvisToClient(@PathVariable("aid") Long avisId, @PathVariable("cid") Long clientId) {
        return avisService.addAvisToClient(avisId, clientId);
    }
}
