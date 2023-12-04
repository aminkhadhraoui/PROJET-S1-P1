package com.Projet.Projet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Admin extends User{


    private String nom;
    private String prenom;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Notification> notifications = new ArrayList<>();
}
