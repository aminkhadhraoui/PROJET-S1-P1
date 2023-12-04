package com.Projet.Projet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String nom;
    @ManyToMany
    @JoinTable(
            name = "tag_formation",
            joinColumns =  @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns =  @JoinColumn(name = "formation_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<Formation> formations = new ArrayList<>();
}
