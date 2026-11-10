package com.quest_api.repository;

import com.quest_api.model.Quest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long> {
    Boolean existsByTitulo(String titulo);
}
