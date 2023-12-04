package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.Formation;
import com.Projet.Projet.Entities.Seance;

import java.util.List;

public interface SeanceService {

    Seance addSeance(Seance seance);
    List<Seance> getAllSeances();
    Seance getSeanceById(Long seanceId);
    Seance updateSeance(Seance seance);
    void deleteSeance(Long seanceId);


}
