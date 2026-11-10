package com.quest_api.dto;

import com.quest_api.model.Quest;

public record QuestResponseDTO(
    Long id,
    String titulo,
    Long xp,
    String loot,
    Long lootQnt,
    boolean concluida){

        public QuestResponseDTO(Quest quest) {
    this(
        quest.getId(),
        quest.getTitulo(),
        quest.getXp(),
        quest.getLoot(),
        quest.getLootQnt(),
        quest.isConcluida()
    );
}
    }