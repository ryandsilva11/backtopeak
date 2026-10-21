package com.gamecore.api.dto;

import java.time.LocalDateTime;

import com.gamecore.api.model.Guilda;

public record GuildaResponseDTO(
        Long id,
        String nome,
        String lema,
        LocalDateTime dataCriacao) {

    public GuildaResponseDTO(Guilda guilda) {
        this(
                guilda.getId(),
                guilda.getNome(),
                guilda.getLema(),
                guilda.getDataCriacao());
    }
}
