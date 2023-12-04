package com.Projet.Projet.Repositories;

import com.Projet.Projet.Entities.Sponsoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsoringRepository extends JpaRepository<Sponsoring, Long> {
}