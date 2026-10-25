package com.gamecore.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ItemRequestDTO(
                @NotBlank(message = "O nome do item é obrigatório") @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres") String nome,

                @NotBlank(message = "O tipo do item é obrigatório") @Size(max = 30, message = "O tipo deve ter no máximo 30 caracteres") String tipo,

                @NotNull(message = "O preço é obrigatório") @PositiveOrZero(message = "O preço não pode ser negativo") Double preco,

                Integer poder, // Opcional (pode ser null se o item for utilitário/cosmético

                @NotNull(message = "A quantidade é obrigatória") @PositiveOrZero(message = "A quantidade não pode ser negativa") Integer quantidade) {
}
