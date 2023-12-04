package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.Offre;
import com.Projet.Projet.Repositories.OffreRepository;
import com.Projet.Projet.Services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OffreServiceImpl implements OffreService {

    @Autowired
    OffreRepository offreRepository;

    @Override
    public List<Offre> getAllOffres(){
        return offreRepository.findAll();
    }

    @Override
    public Offre getOfferById(Long id){
        return offreRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Aucune offre trouvée"));
    }

    @Override
    public Offre addOffer(Offre offre){
        return offreRepository.save(offre);
    }

    @Override
    public Offre editOffre(Long id, Offre offre){
        Offre offreDB = getOfferById(id);

        offreDB.setRemise(offre.getRemise());
        offreDB.setDateDebut(offre.getDateDebut());
        offreDB.setDateFin(offre.getDateFin());

        offreRepository.save(offreDB);

        return offreDB;
    }

    @Override
    public void deleteOffer(Long id){
        offreRepository.deleteById(id);
    }

}
