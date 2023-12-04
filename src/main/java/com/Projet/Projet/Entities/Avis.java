package com.Projet.Projet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String commentaire;
    private int note;
    private LocalDate date;
    @ManyToOne
    @JsonIgnore
    private Formation formation;
    @ManyToOne
    private Client client;
}
