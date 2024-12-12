package com.example.game_service;

import com.example.game_service.controller.ParticipationController;
import com.example.game_service.dto.ParticipationDTO;
import com.example.game_service.entity.Participation;
import com.example.game_service.service.ParticipationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(ParticipationController.class)
public class ParticipationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParticipationService participationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateParticipation() throws Exception {
        ParticipationDTO participationDTO = new ParticipationDTO();
        participationDTO.setIdPartie(1L);
        participationDTO.setIdJoueur(2L);
        participationDTO.setScore(100);
        participationDTO.setVictoire(true);

        Participation participation = new Participation();
        participation.setId(1L);
        participation.setIdPartie(1L);
        participation.setIdJoueur(2L);
        participation.setScore(100);
        participation.setVictoire(true);

        when(participationService.createParticipation(Mockito.any(ParticipationDTO.class))).thenReturn(participation);

        mockMvc.perform(post("/Participation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(participationDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.idPartie").value(1L))
                .andExpect(jsonPath("$.idJoueur").value(2L))
                .andExpect(jsonPath("$.score").value(100))
                .andExpect(jsonPath("$.victoire").value(true));

        verify(participationService, times(1)).createParticipation(Mockito.any(ParticipationDTO.class));
    }

    @Test
    public void testGetParticipationById_Found() throws Exception {
        Participation participation = new Participation();
        participation.setId(1L);
        participation.setIdPartie(1L);
        participation.setIdJoueur(2L);
        participation.setScore(50);
        participation.setVictoire(false);

        when(participationService.getParticipationById(1L)).thenReturn(participation);

        mockMvc.perform(get("/Participation/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.idPartie").value(1L))
                .andExpect(jsonPath("$.idJoueur").value(2L))
                .andExpect(jsonPath("$.score").value(50))
                .andExpect(jsonPath("$.victoire").value(false));

        verify(participationService, times(1)).getParticipationById(1L);
    }

    @Test
    public void testGetParticipationById_NotFound() throws Exception {
        when(participationService.getParticipationById(1L)).thenReturn(null);

        mockMvc.perform(get("/Participation/1"))
                .andExpect(status().isNotFound());

        verify(participationService, times(1)).getParticipationById(1L);
    }

    @Test
    public void testGetAllParticipations() throws Exception {
        Participation participation1 = new Participation();
        participation1.setId(1L);

        Participation participation2 = new Participation();
        participation2.setId(2L);

        List<Participation> participations = Arrays.asList(participation1, participation2);

        when(participationService.getAllParticipations()).thenReturn(participations);

        mockMvc.perform(get("/Participation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));

        verify(participationService, times(1)).getAllParticipations();
    }
}
