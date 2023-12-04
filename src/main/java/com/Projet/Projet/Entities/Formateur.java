package com.Projet.Projet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Formateur extends User{
    private String nom;
    private String prenom;
    private String photo;
    @ManyToMany(mappedBy = "formateurs")
    @JsonIgnore
    private List<Formation> formations = new ArrayList<>();
}
