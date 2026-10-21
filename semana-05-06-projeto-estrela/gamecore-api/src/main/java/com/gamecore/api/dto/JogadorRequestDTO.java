package com.gamecore.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record JogadorRequestDTO(

                @NotBlank(message = "O nome do jogador é obrigatório.") @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.") String nome,

                @NotBlank(message = "A classe do jogador é obrigatória.") String classe

) {
}
