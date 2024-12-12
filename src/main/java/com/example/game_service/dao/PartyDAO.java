package com.example.game_service.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.game_service.entity.Party;
import com.example.game_service.repository.PartyRepository;

@Component
public class PartyDAO {

    @Autowired
    private PartyRepository partyRepository;

    public Party saveParty(Party party) {
        return partyRepository.save(party);
    }

    public Optional<Party> findPartyById(Long id) {
        return partyRepository.findById(id);
    }

    public void deletePartyById(Long id) {
        partyRepository.deleteById(id);
    }

}