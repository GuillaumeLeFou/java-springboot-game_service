package com.example.game_service.controller;

import com.example.game_service.dto.ParticipationDTO;
import com.example.game_service.entity.Participation;
import com.example.game_service.service.ParticipationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Participation")
public class ParticipationController {

    @Autowired
    private ParticipationService participationService;

    @PostMapping
    public ResponseEntity<Participation> createParticipation(@RequestBody ParticipationDTO participationDTO) {
        Participation participation = participationService.createParticipation(participationDTO);
        return ResponseEntity.ok(participation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participation> getParticipationById(@PathVariable Long id) {
        Participation participation = participationService.getParticipationById(id);
        return participation != null ? ResponseEntity.ok(participation) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Participation>> getAllParticipations() {
        List<Participation> participations = participationService.getAllParticipations();
        return ResponseEntity.ok(participations);
    }
}
