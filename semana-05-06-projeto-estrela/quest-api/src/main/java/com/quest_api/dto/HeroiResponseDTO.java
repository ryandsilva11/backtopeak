package com.quest_api.dto;

import com.quest_api.model.Heroi;

public record HeroiResponseDTO(
        Long id,
        String nome,
        String classe,
        Long nivel,
        Long xp) {

    public HeroiResponseDTO(Heroi heroi) {
        this(heroi.getId(), heroi.getNome(), heroi.getClasse(), heroi.getNivel(), heroi.getXp());
    }
}