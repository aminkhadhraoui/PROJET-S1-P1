package com.Projet.Projet.Entities;

import com.Projet.Projet.Enum.EtatDemandeInscription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class CentreFormation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String adresse;
    private int tel;
    private String email;
    private long rib;
    private String matriculeFiscal;
    private LocalDate dateCreation;
    private String logo;
    @ManyToMany
    @JoinTable(
            name = "centre_categorie",
            joinColumns =  @JoinColumn(name = "centre_id", referencedColumnName = "id"),
            inverseJoinColumns =  @JoinColumn(name = "categorie_id", referencedColumnName = "id"))
    private List<Categorie> categorie = new ArrayList<>();
    @Column(columnDefinition="TEXT",length = 5000)
    private String description;
    @Enumerated
    private EtatDemandeInscription etatDemandeInscription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "centreFormation")
    private List<Formation> formations = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "centreFormation")
    @JsonIgnore
    private List<Abonnement> abonnements = new ArrayList<>();

    @OneToMany
    @JsonIgnore
    private List<Offre> offres = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionCentre> transactions = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "centreFormation")
    @JsonIgnore
    private List<Notification> notifications = new ArrayList<>();
    @ManyToOne
    private Manager manager;
}
