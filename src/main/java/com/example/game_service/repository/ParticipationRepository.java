package com.example.game_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.game_service.entity.Participation;
import com.example.game_service.entity.Party;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long>{
    List<Participation> findAll();
    Optional<Participation> findById(Long id);
    Participation save(Participation participation);
    void deleteById(Long id);
}
