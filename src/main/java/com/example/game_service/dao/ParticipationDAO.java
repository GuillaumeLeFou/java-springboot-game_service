package com.example.game_service.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.game_service.client.PlayerServiceClient;
import com.example.game_service.entity.Participation;
import com.example.game_service.repository.ParticipationRepository;

@Component
public class ParticipationDAO {

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private PlayerServiceClient playerServiceClient;

    public Participation saveParticipation(Participation participation) {
        return participationRepository.save(participation);
    }

    public void updatePlayerStats(Long playerId, int score, boolean victoire) {
        playerServiceClient.updatePlayer(playerId, score, victoire);
    }

    public void deleteParticipation(Long id) {
        participationRepository.deleteById(id);
    }

    public Participation getParticipationById(Long id) {
        return participationRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id){
        participationRepository.deleteById(id);
    }

    public List<Participation> findAll(){
        return participationRepository.findAll();
    } 

    public Optional<Participation> findById(Long id){
        return participationRepository.findById(id);
    } 

    public Participation save(Participation participation){
        return participationRepository.save(participation);
    }
}