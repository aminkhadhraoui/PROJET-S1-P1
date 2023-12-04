package com.Projet.Projet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Manager extends User {
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private int tel;
    private String img;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
    @JsonIgnore
    private List<CentreFormation> centreFormation = new ArrayList<>();
}
