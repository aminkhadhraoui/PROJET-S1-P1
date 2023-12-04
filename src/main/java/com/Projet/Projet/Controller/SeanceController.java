package com.Projet.Projet.Controller;

import com.Projet.Projet.Entities.Seance;
import com.Projet.Projet.Services.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seance")
@CrossOrigin(origins = "*")
public class SeanceController {
    @Autowired
    private SeanceService seanceService;

    @GetMapping("")
    public List<Seance> getAllSeances() {
        return seanceService.getAllSeances();
    }

    @GetMapping("/{id}")
    public Seance getSeanceById(@PathVariable("id") Long seanceId){
        return seanceService.getSeanceById(seanceId);
    }
    @PostMapping("/add")
    public Seance addSeance(@RequestBody Seance seance){
        return seanceService.addSeance(seance);
    }
    @PutMapping("/update")
    public Seance updateSeance(@RequestBody Seance seance){
        return seanceService.updateSeance(seance);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteSeance(@PathVariable("id") Long seanceId){
        seanceService.deleteSeance(seanceId);
    }

}
