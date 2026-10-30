package com.quest_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record HeroiRequestDTO(
        @NotBlank(message = "O nome é obrigatório") @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres") String nome,
        @NotBlank(message = "A classe é obrigatória") String classe) {
}