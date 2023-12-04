package com.Projet.Projet.Controller;

import com.Projet.Projet.Entities.Sponsoring;
import com.Projet.Projet.Services.SponsoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Sponsoring")
@CrossOrigin(origins = "*")
public class SponsoringController {
    @Autowired
    private SponsoringService sponsoringService;


    @GetMapping("")
    public List<Sponsoring> getAllSponsoring(){
        return sponsoringService.getAllSponsoring();
    }
    @GetMapping("/{id}")
    public Sponsoring getSponsoringById(@PathVariable("id") Long id){

        return sponsoringService.getSponsoringById(id);
    }
    @PostMapping("/add")
    public Sponsoring addSponsoring(@RequestBody Sponsoring sponsoring){
        return sponsoringService.addSponsoring(sponsoring);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteSponsoring(@PathVariable("id") Long id){
        return sponsoringService.deleteSponsoringById(id);
    }

    @GetMapping("/{sid}/formation/{fid}")
    public Sponsoring addSponsoringToFormation(@PathVariable("sid") Long sId,@PathVariable("fid") Long fId){
        return sponsoringService.addSponsoringToFormation(sId,fId);
    }
    @PutMapping("/update")
    public String updateSponsoring(@RequestBody Sponsoring sponsoring){
        return sponsoringService.updateSponsoring(sponsoring);
    }
}
