package com.Projet.Projet.Repositories;

import com.Projet.Projet.Entities.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisRepository extends JpaRepository<Avis,Long> {
}
