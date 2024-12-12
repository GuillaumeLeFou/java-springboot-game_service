package com.example.game_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ParticipationDTO {
    private Long idPartie;
    private Long idJoueur;
    private int score;
    private boolean victoire;
}