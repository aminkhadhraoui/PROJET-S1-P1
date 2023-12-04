package com.Projet.Projet.Repositories;

import com.Projet.Projet.Entities.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormateurRespository extends JpaRepository<Formateur, Long> {
}
