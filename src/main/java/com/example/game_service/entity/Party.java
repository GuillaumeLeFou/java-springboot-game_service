package com.example.game_service.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Party {
    // Partie : id, date, type_partie, score_maximum, id_hote
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // private LocalDateTime date;

    private String typePartie;

    private int scoreMaximum;

    private Long idHote;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
