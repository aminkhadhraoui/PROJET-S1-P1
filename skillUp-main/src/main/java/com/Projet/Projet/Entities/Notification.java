package com.Projet.Projet.Entities;

import com.Projet.Projet.Enum.EtatNotification;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String contenu;
    private LocalDate date;
    @Enumerated
    private EtatNotification etat;
    @ManyToOne
    private Client client;
    @ManyToOne
    private CentreFormation centreFormation;
    @ManyToOne
    private Admin admin;

}
