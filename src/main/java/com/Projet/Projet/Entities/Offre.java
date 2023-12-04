package com.Projet.Projet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int remise;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @ManyToOne
    @JsonIgnore
    private Formation formation;
    @ManyToOne
    private CentreFormation centreFormation;

}
