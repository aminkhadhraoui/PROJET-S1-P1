package com.Projet.Projet.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(mappedBy = "discussions")
    private List<User> users = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

}
