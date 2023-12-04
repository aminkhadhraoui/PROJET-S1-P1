package com.Projet.Projet.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class DemandeInscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Formation formation;
}
