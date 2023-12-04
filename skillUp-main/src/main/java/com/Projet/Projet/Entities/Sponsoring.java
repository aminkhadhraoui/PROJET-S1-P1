package com.Projet.Projet.Entities;

import com.Projet.Projet.Enum.EtatSponsoring;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Sponsoring {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @Enumerated
    private EtatSponsoring etat;
    @ManyToOne
    @JsonIgnore
    private Formation formation;
}
