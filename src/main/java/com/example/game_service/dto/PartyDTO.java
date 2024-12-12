package com.example.game_service.dto;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PartyDTO {
    private LocalDateTime date;
    private String typePartie;
    private int scoreMaximum;
    private Long idHote;
}
