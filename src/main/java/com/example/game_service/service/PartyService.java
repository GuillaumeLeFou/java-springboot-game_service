package com.example.game_service.service;

import com.example.game_service.dto.PartyDTO;
import com.example.game_service.entity.Party;
import com.example.game_service.repository.PartyRepository;
import com.example.game_service.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyService implements IPartyService {

    @Autowired
    private PartyRepository partyRepository;

    @Override
    public Party createParty(PartyDTO partyDTO) {
        Party party = new Party();
        party.setCreatedAt(partyDTO.getDate());
        party.setTypePartie(partyDTO.getTypePartie());
        party.setScoreMaximum(partyDTO.getScoreMaximum());
        party.setIdHote(partyDTO.getIdHote());
        return partyRepository.save(party);
    }

    @Override
    public Party getPartyById(Long id) {
        return partyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Party> getAllParties() {
        return partyRepository.findAll();
    }

    @Override
    public void deleteParty(Long id) {
        partyRepository.deleteById(id);
    }
}
