package com.Projet.Projet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Client extends User{

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private int tel;
    private String adresse;
    private String fonction;
    private String img;
    @ManyToMany
    @JoinTable(
            name = "client_formation",
            joinColumns =  @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns =  @JoinColumn(name = "formation_id", referencedColumnName = "id"))
    private List<Formation> formations = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "client")
    @JsonIgnore
    private List<DemandeInscription> demandeInscriptions = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "client")
    @JsonIgnore
    private List<TransactionClient> transactions = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "client")
    @JsonIgnore
    private List<Avis> avis = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "client")
    @JsonIgnore
    private List<Notification> notifications = new ArrayList<>();
}
