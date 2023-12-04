package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.Seance;
import com.Projet.Projet.Repositories.SeanceRepository;
import com.Projet.Projet.Services.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SeanceServiceImpl implements SeanceService {
    @Autowired
    private SeanceRepository seanceRepository;

    @Override
    public Seance addSeance(Seance seance) {
        return seanceRepository.save(seance);
    }

    @Override
    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }

    @Override
    public Seance getSeanceById(Long seanceId) {
        return seanceRepository.findById(seanceId).orElseThrow(
                () -> new NoSuchElementException("Aucune seance avec ID: " + seanceId));
    }

    @Override
    public Seance updateSeance(Seance seance) {
        if (!seanceRepository.existsById(seance.getId())) {
            throw new NoSuchElementException("Aucune seance avec ID : " + seance.getId());
        }
        Seance seanceUpdated = getSeanceById(seance.getId());
        seanceUpdated.setDate(seance.getDate());
        seanceUpdated.setHeureDebut(seance.getHeureDebut());
        seanceUpdated.setHeureFin(seance.getHeureFin());
        return seanceRepository.save(seanceUpdated);
    }

    @Override
    public void deleteSeance(Long seanceId) {
        seanceRepository.deleteById(seanceId);
    }
}
