package com.gamecore.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CompraRequestDTO(
        @NotNull(message = "O ID do jogador é obrigatório") Long jogadorId,

        @NotNull(message = "O ID do item é obrigatório") Long itemId,

        @NotNull(message = "A quantidade é obrigatória") @Positive(message = "A quantidade deve ser maior que zero") Integer quantidade) {
}
