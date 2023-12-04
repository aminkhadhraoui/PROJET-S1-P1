package com.Projet.Projet.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition="TEXT",length = 5000)
    private String contenu;
    private LocalDate date;
    private String heure;
    @ManyToOne
    @JsonIgnore
    private User user;
}
