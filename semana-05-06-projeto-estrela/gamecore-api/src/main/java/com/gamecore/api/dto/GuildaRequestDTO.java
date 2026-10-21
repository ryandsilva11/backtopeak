package com.gamecore.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GuildaRequestDTO(
        @NotBlank(message = "O nome da guilda é obrigatório.") @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.") String nome,

        @NotBlank(message = "O lema da guilda é obrigatório.") String lema) {
}
