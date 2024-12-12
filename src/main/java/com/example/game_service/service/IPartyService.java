package com.example.game_service.service;

import com.example.game_service.dto.PartyDTO;
import com.example.game_service.entity.Party;

import java.util.List;

public interface IPartyService {
    Party createParty(PartyDTO partyDTO);
    Party getPartyById(Long id);
    List<Party> getAllParties();
    void deleteParty(Long id);
}
