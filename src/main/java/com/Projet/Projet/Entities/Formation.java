package com.Projet.Projet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    @Column(columnDefinition="TEXT",length = 5000)
    private String description;
    private String img;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double prix;
    private Integer nbMaxCan;

    @ManyToOne
    @JsonIgnore
    private CentreFormation centreFormation;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "formation")
    private List<Avis> avis = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "formation")
    private List<Offre> offres = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "formation")
    private List<Seance> seances = new ArrayList<>();

    @ManyToMany()
    @JoinTable(
            name = "formation_formateur",
            joinColumns =  @JoinColumn(name = "formation_id", referencedColumnName = "id"),
            inverseJoinColumns =  @JoinColumn(name = "formateur_id", referencedColumnName = "id"))
    private List<Formateur> formateurs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "formation")
    private List<Sponsoring> sponsorings;

    @ManyToOne
    @JsonIgnore
    private Categorie categorie;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "formation")
    @JsonIgnore
    private List<DemandeInscription> demandes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "formation")
    @JsonIgnore
    private List<TransactionClient> transactionClients = new ArrayList<>();

    @ManyToMany(mappedBy = "formations")
    @JsonIgnore
    private List<Client> clients = new ArrayList<>();

    @ManyToMany(mappedBy = "formations")
    private Set<Tag> tags = new HashSet<>();
}
