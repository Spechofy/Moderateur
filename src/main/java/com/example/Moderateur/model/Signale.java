package com.example.Moderateur.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "signale")
@Getter
@Setter
public class Signale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Moderateur moderateur;

    @ManyToOne
    private Motif motif;

    private LocalDateTime dateSignalement;

    private String commentaire;

    // Getters/setters
}
