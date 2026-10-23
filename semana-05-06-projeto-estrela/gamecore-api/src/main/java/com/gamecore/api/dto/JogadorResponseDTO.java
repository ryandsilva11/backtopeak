package com.gamecore.api.dto;

import com.gamecore.api.model.Jogador;

public record JogadorResponseDTO(
        Long id,
        String nome,
        String classe,
        Integer nivel,
        Integer pontosVida,
        Integer pontosMana,
        Double moedasOuro,
        String nomeGuilda) {
    // Construtor de conveniência: transforma uma Entidade Jogador diretamente em
    // DTO!
    public JogadorResponseDTO(Jogador jogador) {
        this(
                jogador.getId(),
                jogador.getNome(),
                jogador.getClasse(),
                jogador.getNivel(),
                jogador.getPontosVida(),
                jogador.getPontosMana(),
                jogador.getMoedasOuro(),
                jogador.getGuilda() != null ? jogador.getGuilda().getNome() : null);
    }
}
