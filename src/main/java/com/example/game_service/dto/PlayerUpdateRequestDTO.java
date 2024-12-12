package com.example.game_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PlayerUpdateRequestDTO {
    private Long playerId;
    private int score;
    private boolean victoire;

    public PlayerUpdateRequestDTO(Long playerId, int score, boolean victoire) {
        this.playerId = playerId;
        this.score = score;
        this.victoire = victoire;
    }
}
