package com.example.game_service;

import com.example.game_service.controller.PartyController;
import com.example.game_service.dto.PartyDTO;
import com.example.game_service.entity.Party;
import com.example.game_service.service.PartyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PartyController.class)
public class PartyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartyService partyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateParty() throws Exception {
        // Préparation des données
        PartyDTO partyDTO = new PartyDTO();
        partyDTO.setDate(java.time.LocalDateTime.now());
        partyDTO.setTypePartie("Arcade");
        partyDTO.setScoreMaximum(100);
        partyDTO.setIdHote(1L);

        Party party = new Party();
        party.setId(1L);
        party.setTypePartie("Arcade");
        party.setScoreMaximum(100);
        party.setIdHote(1L);

        // Configurer le mock
        when(partyService.createParty(Mockito.any(PartyDTO.class))).thenReturn(party);

        // Effectuer la requête et vérifier la réponse
        mockMvc.perform(post("/Party")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(partyDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.typePartie").value("Arcade"))
                .andExpect(jsonPath("$.scoreMaximum").value(100))
                .andExpect(jsonPath("$.idHote").value(1L));

        verify(partyService, times(1)).createParty(Mockito.any(PartyDTO.class));
    }

    @Test
    public void testGetPartyById_Found() throws Exception {
        // Préparation des données
        Party party = new Party();
        party.setId(1L);
        party.setTypePartie("Arcade");
        party.setScoreMaximum(100);
        party.setIdHote(1L);

        // Configurer le mock
        when(partyService.getPartyById(1L)).thenReturn(party);

        // Effectuer la requête et vérifier la réponse
        mockMvc.perform(get("/Party/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.typePartie").value("Arcade"))
                .andExpect(jsonPath("$.scoreMaximum").value(100))
                .andExpect(jsonPath("$.idHote").value(1L));

        verify(partyService, times(1)).getPartyById(1L);
    }

    @Test
    public void testGetPartyById_NotFound() throws Exception {
        // Configurer le mock
        when(partyService.getPartyById(1L)).thenReturn(null);

        // Effectuer la requête et vérifier la réponse
        mockMvc.perform(get("/Party/1"))
                .andExpect(status().isNotFound());

        verify(partyService, times(1)).getPartyById(1L);
    }

    @Test
    public void testDeleteParty() throws Exception {
        // Configurer le mock
        doNothing().when(partyService).deleteParty(1L);

        // Effectuer la requête et vérifier la réponse
        mockMvc.perform(delete("/Party/1"))
                .andExpect(status().isNoContent());

        verify(partyService, times(1)).deleteParty(1L);
    }
}
