package com.example.Moderateur.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "signale")
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

    public Motif getMotif() {
        return motif;
    }

    public void setMotif(Motif motif) {
        this.motif = motif;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Moderateur getModerateur() {
        return moderateur;
    }

    public void setModerateur(Moderateur moderateur) {
        this.moderateur = moderateur;
    }

    public LocalDateTime getDateSignalement() {
        return dateSignalement;
    }

    public void setDateSignalement(LocalDateTime dateSignalement) {
        this.dateSignalement = dateSignalement;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


    // Getters/setters
}
