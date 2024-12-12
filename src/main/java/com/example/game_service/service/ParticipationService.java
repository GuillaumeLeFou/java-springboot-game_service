package com.example.game_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.game_service.client.PlayerServiceClient;
import com.example.game_service.dao.ParticipationDAO;
import com.example.game_service.dao.PartyDAO;
import com.example.game_service.dto.ParticipationDTO;
import com.example.game_service.dto.PartyDTO;
import com.example.game_service.entity.Participation;
import com.example.game_service.entity.Party;
import com.example.game_service.repository.PartyRepository;

@Service
public class ParticipationService implements IParticipationService{

    @Autowired
    private ParticipationDAO participationDAO;

    @Autowired
    private PartyDAO partyDAO;

    @Autowired
    private PlayerServiceClient playerServiceClient;

    @Override
    public Participation createParticipation(ParticipationDTO participationDTO) {
        Participation participation = new Participation();
        participation.setIdPartie(participationDTO.getIdPartie());
        participation.setIdJoueur(participationDTO.getIdJoueur());
        participation.setScore(participationDTO.getScore());
        participation.setVictoire(participationDTO.isVictoire());

        playerServiceClient.updatePlayer(participationDTO.getIdJoueur(), participationDTO.getScore(), participationDTO.isVictoire());
        return participationDAO.save(participation);
    }

    @Override
    public Participation getParticipationById(Long id) {
        return participationDAO.findById(id).orElse(null);
    }

    @Override
    public List<Participation> getAllParticipations() {
        return participationDAO.findAll();
    }

    @Override
    public void deleteParticipation(Long id) {
        participationDAO.deleteById(id);
    }
}
