package com.Projet.Projet.Repositories;

import com.Projet.Projet.Entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Long> {
}
