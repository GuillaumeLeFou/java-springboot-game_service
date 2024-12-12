package com.example.game_service;

import com.example.game_service.client.PlayerServiceClient;
import com.example.game_service.dao.ParticipationDAO;
import com.example.game_service.dao.PartyDAO;
import com.example.game_service.dto.ParticipationDTO;
import com.example.game_service.entity.Participation;
import com.example.game_service.service.ParticipationService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParticipationServiceTest {

    @Mock
    private ParticipationDAO participationDAO;

    @Mock
    private PartyDAO partyDAO;

    @Mock
    private PlayerServiceClient playerServiceClient;

    @InjectMocks
    private ParticipationService participationService;

    @Test
    public void testCreateParticipation() {
        // Préparation des données
        ParticipationDTO participationDTO = new ParticipationDTO();
        participationDTO.setIdPartie(1L);
        participationDTO.setIdJoueur(2L);
        participationDTO.setScore(100);
        participationDTO.setVictoire(true);

        Participation savedParticipation = new Participation();
        savedParticipation.setId(1L);
        savedParticipation.setIdPartie(1L);
        savedParticipation.setIdJoueur(2L);
        savedParticipation.setScore(100);
        savedParticipation.setVictoire(true);

        // Configurer les mocks
        when(participationDAO.save(any(Participation.class))).thenReturn(savedParticipation);
        doNothing().when(playerServiceClient).updatePlayer(2L, 100, true);

        // Appel du service
        Participation result = participationService.createParticipation(participationDTO);

        // Vérifications
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getIdPartie());
        assertEquals(2L, result.getIdJoueur());
        assertEquals(100, result.getScore());
        assertTrue(result.isVictoire());
        verify(playerServiceClient, times(1)).updatePlayer(2L, 100, true);
        verify(participationDAO, times(1)).save(any(Participation.class));
    }

    @Test
    public void testGetParticipationById_Found() {
        // Préparation des données
        Participation participation = new Participation();
        participation.setId(1L);
        participation.setIdPartie(1L);
        participation.setIdJoueur(2L);
        participation.setScore(50);
        participation.setVictoire(false);

        // Configurer le mock
        when(participationDAO.findById(1L)).thenReturn(Optional.of(participation));

        // Appel du service
        Participation result = participationService.getParticipationById(1L);

        // Vérifications
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getIdPartie());
        assertEquals(2L, result.getIdJoueur());
        assertEquals(50, result.getScore());
        assertFalse(result.isVictoire());
        verify(participationDAO, times(1)).findById(1L);
    }

    @Test
    public void testGetParticipationById_NotFound() {
        // Configurer le mock
        when(participationDAO.findById(1L)).thenReturn(Optional.empty());

        // Appel du service
        Participation result = participationService.getParticipationById(1L);

        // Vérifications
        assertNull(result);
        verify(participationDAO, times(1)).findById(1L);
    }

    @Test
    public void testGetAllParticipations() {
        // Préparation des données
        Participation participation1 = new Participation();
        participation1.setId(1L);

        Participation participation2 = new Participation();
        participation2.setId(2L);

        List<Participation> participations = Arrays.asList(participation1, participation2);

        // Configurer le mock
        when(participationDAO.findAll()).thenReturn(participations);

        // Appel du service
        List<Participation> result = participationService.getAllParticipations();

        // Vérifications
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(participationDAO, times(1)).findAll();
    }

    @Test
    public void testDeleteParticipation() {
        // Appel du service
        participationService.deleteParticipation(1L);

        // Vérifications
        verify(participationDAO, times(1)).deleteById(1L);
    }
}
