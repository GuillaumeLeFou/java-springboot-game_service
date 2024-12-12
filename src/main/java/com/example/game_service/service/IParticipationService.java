package com.example.game_service.service;

import java.util.List;

import com.example.game_service.dto.ParticipationDTO;
import com.example.game_service.entity.Participation;

public interface IParticipationService {
    Participation createParticipation(ParticipationDTO participationDTO);
    Participation getParticipationById(Long id);
    List<Participation> getAllParticipations();
    void deleteParticipation(Long id);
}
