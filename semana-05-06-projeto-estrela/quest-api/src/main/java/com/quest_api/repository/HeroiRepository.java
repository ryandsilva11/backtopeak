package com.quest_api.repository;

import java.util.Optional;
import com.quest_api.model.Heroi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroiRepository extends JpaRepository<Heroi, Long> {

    Boolean existsByNome(String nome);
} 