package com.example.game_service.controller;
import com.example.game_service.dto.PartyDTO;
import com.example.game_service.entity.Party;
import com.example.game_service.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Party")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @PostMapping
    public ResponseEntity<Party> createParty(@RequestBody PartyDTO partyDTO) {
        Party party = partyService.createParty(partyDTO);
        return ResponseEntity.ok(party);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Party> getPartyById(@PathVariable Long id) {
        Party party = partyService.getPartyById(id);
        return party != null ? ResponseEntity.ok(party) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParty(@PathVariable Long id) {
        partyService.deleteParty(id);
        return ResponseEntity.noContent().build();
    }
}