package com.example.game_service;

import com.example.game_service.dto.PartyDTO;
import com.example.game_service.entity.Party;
import com.example.game_service.repository.PartyRepository;
import com.example.game_service.service.PartyService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PartyServiceTest {

    @Mock
    private PartyRepository partyRepository;

    @InjectMocks
    private PartyService partyService;

    @Test
    public void testCreateParty() {
        PartyDTO partyDTO = new PartyDTO();
        partyDTO.setDate(LocalDateTime.now());
        partyDTO.setTypePartie("Arcade");
        partyDTO.setScoreMaximum(100);
        partyDTO.setIdHote(1L);

        Party savedParty = new Party();
        savedParty.setId(1L);
        savedParty.setCreatedAt(partyDTO.getDate());
        savedParty.setTypePartie("Arcade");
        savedParty.setScoreMaximum(100);
        savedParty.setIdHote(1L);

        when(partyRepository.save(any(Party.class))).thenReturn(savedParty);

        Party result = partyService.createParty(partyDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Arcade", result.getTypePartie());
        assertEquals(100, result.getScoreMaximum());
        assertEquals(1L, result.getIdHote());
        verify(partyRepository, times(1)).save(any(Party.class));
    }

    @Test
    public void testGetPartyById_Found() {
        Party party = new Party();
        party.setId(1L);
        party.setTypePartie("Classic");
        party.setScoreMaximum(50);
        party.setIdHote(2L);

        when(partyRepository.findById(1L)).thenReturn(Optional.of(party));

        Party result = partyService.getPartyById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Classic", result.getTypePartie());
        assertEquals(50, result.getScoreMaximum());
        verify(partyRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetPartyById_NotFound() {
        when(partyRepository.findById(1L)).thenReturn(Optional.empty());

        Party result = partyService.getPartyById(1L);

        assertNull(result);
        verify(partyRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllParties() {
        Party party1 = new Party();
        party1.setId(1L);
        party1.setTypePartie("Classic");

        Party party2 = new Party();
        party2.setId(2L);
        party2.setTypePartie("Arcade");

        List<Party> parties = Arrays.asList(party1, party2);

        when(partyRepository.findAll()).thenReturn(parties);

        List<Party> result = partyService.getAllParties();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(partyRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteParty() {
        partyService.deleteParty(1L);

        verify(partyRepository, times(1)).deleteById(1L);
    }
}
