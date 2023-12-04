package com.Projet.Projet.Controller;

import com.Projet.Projet.Entities.DemandeInscription;
import com.Projet.Projet.Services.DemandeInscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demande")
@CrossOrigin(origins = "*")
public class DemandeIscriptionController {
    @Autowired
    private DemandeInscriptionService demandeInscriptionService;

    @GetMapping("")
    public List<DemandeInscription> getAllDemandes(){
        return demandeInscriptionService.getAllDemandes();
    }
    @GetMapping("/{id}")
    public DemandeInscription getDemandeById(@PathVariable("id") Long demandeId){
        return demandeInscriptionService.getDemandeById(demandeId);
    }
    @PostMapping("/add")
    public DemandeInscription addDemande(@RequestBody DemandeInscription demandeInscription){
        return demandeInscriptionService.addDemande(demandeInscription);
    }
    @PutMapping("/update")
    public DemandeInscription updateDemande(@RequestBody DemandeInscription demandeInscription){
        return demandeInscriptionService.updateDemande(demandeInscription);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteDemande(@PathVariable("id") Long demandeId){
        demandeInscriptionService.deleteDemande(demandeId);
    }
    @GetMapping("/client/{cid}/demande/{did}")
    public DemandeInscription addDemandeToClient(@PathVariable("cid") Long clientId, @PathVariable("did") Long demandeId){
        return demandeInscriptionService.addDemandeToClient(clientId,demandeId);
    }
    @GetMapping("/formation/{fid}/demande/{did}")
    public DemandeInscription addDemandeToFormation(@PathVariable("fid") Long formationId, @PathVariable("did") Long demandeId){
        return demandeInscriptionService.addDemandeToFormation(formationId,demandeId);
    }
}
