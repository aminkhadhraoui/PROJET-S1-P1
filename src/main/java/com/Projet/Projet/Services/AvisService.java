package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.Avis;

import java.util.List;

public interface AvisService {

    Avis addAvis(Avis avis);
    List<Avis> getAllAvis();
    Avis getAvisById(Long avisId);
    Avis updateAvis(Avis avis);
    void deleteAvis(Long avisId);
    Avis addAvisToFormation(Long avisId, Long formationId);
    Avis addAvisToClient(Long avisId, Long clientId);
}
