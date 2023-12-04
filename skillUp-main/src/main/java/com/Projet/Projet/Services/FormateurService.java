package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.Formateur;

import java.util.List;
import java.util.Optional;

public interface FormateurService {

    List<Formateur> getAllFormateurs();
    Formateur getFormateurById(Long id);
    Formateur addFormateur(Formateur formateur);
    Formateur addFormateur(Optional<Formateur> formateur);
    Formateur updateFormateur(Long id, Formateur formateur);
     void deleteFormateur(Long id);
}
