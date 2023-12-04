package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.Sponsoring;

import java.util.List;

public interface SponsoringService {
    Sponsoring addSponsoring(Sponsoring sponsoring);
    Sponsoring getSponsoringById(Long id);
    List<Sponsoring> getAllSponsoring();
    String deleteSponsoringById(Long id);
    String updateSponsoring(Sponsoring sponsoring);
    Sponsoring addSponsoringToFormation(Long sid,Long fId);
}
