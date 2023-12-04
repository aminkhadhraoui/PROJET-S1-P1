package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.Formation;
import com.Projet.Projet.Entities.Sponsoring;
import com.Projet.Projet.Repositories.SponsoringRepository;
import com.Projet.Projet.Services.FormationService;
import com.Projet.Projet.Services.SponsoringService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
@AllArgsConstructor
public class sponsoringServiceImpl implements SponsoringService {

    private SponsoringRepository sponsoringRepository;
    private FormationService formationService;
    @Override
    public Sponsoring addSponsoring(Sponsoring sponsoring) {
           return sponsoringRepository.save(sponsoring);
    }

    @Override
    public String deleteSponsoringById(Long id) {
        Sponsoring sponsoring=sponsoringRepository.findById(id).orElse(null);
        if (sponsoring!=null){
            sponsoringRepository.delete(sponsoring);
            return "sponsoring supprimé";
        }else
            return "sponsoring n'existe pas";
    }

    @Override
    public List<Sponsoring> getAllSponsoring() {
        return sponsoringRepository.findAll();
    }

    @Override
    public Sponsoring getSponsoringById(Long id) {
        return sponsoringRepository.findById(id).orElseThrow(()->new NoSuchElementException("sponsoring id not exist"+id));
    }

    @Override
    public String updateSponsoring(Sponsoring sponsoring) {
        Sponsoring sponsoring1=sponsoringRepository.findById(sponsoring.getId()).orElse(null);
        if (sponsoring1 !=null){
            sponsoringRepository.save(sponsoring);
            return "sponsoring modifier avec succé";
        }else
            return "sposoring non existe avec l'id"+sponsoring.getId();
    }

    @Override
    public Sponsoring addSponsoringToFormation(Long sponId, Long fId) {
        Sponsoring sponsoring=getSponsoringById(sponId);
        Formation formation=formationService.getFormationById(fId);
        sponsoring.setFormation(formation);
        updateSponsoring(sponsoring);
        formation.getSponsorings().add(sponsoring);
        formationService.updateFormation(formation);
        return sponsoring;
    }
}
