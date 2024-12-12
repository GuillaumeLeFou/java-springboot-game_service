package com.example.game_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.game_service.entity.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {
    List<Party> findAll();
    Optional<Party> findById(Long id);
    Party save(Party party);
    void deleteById(Long id);
}
