package com.Projet.Projet.Controller;

import com.Projet.Projet.Entities.Offre;
import com.Projet.Projet.Services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offre")
@CrossOrigin(origins = "*")
public class OffreController {

    @Autowired
    OffreService offreService;

    @GetMapping("")
    List<Offre> getAllOffers(){
        return offreService.getAllOffres();
    }

    @GetMapping("/{id}")
    Offre getoffreById(@PathVariable Long id){
        return offreService.getOfferById(id);
    }

    @PostMapping("/add")
    Offre addOffer(@RequestBody Offre offre){
        return offreService.addOffer(offre);
    }

    @PutMapping("/edit/{id}")
    Offre getOfferById(@PathVariable Long id,@RequestBody Offre offre){
        return offreService.editOffre(id,offre);
    }

    @DeleteMapping("/delete/{id}")
    void deleteOffer(@PathVariable Long id) {
        offreService.deleteOffer(id);
    }
}
