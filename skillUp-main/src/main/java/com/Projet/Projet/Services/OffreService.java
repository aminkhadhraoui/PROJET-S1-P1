package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.Offre;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OffreService {

    List<Offre> getAllOffres ();

    Offre getOfferById(Long id);

    Offre addOffer(@RequestBody Offre offre);

    Offre editOffre(Long id, Offre offre);

    void deleteOffer(Long id);
}
