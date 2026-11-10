package com.quest_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record QuestRequestDTO(
        @NotBlank(message = "O título é obrigatório") @Size(min = 2, max = 50, message = "O título da quest deve ter entre 2 e 50 caracteres.") String titulo,

        @NotNull(message = "A quest precisa dar algum xp") @Min(value = 0, message = "O xp precisa ser >= 0.s") Long xp,

        String loot,

        Long lootQnt) {

    @JsonIgnore
    @AssertTrue(message = "Caso haja algum loot, a quantidade deve ser informada")
    public boolean isLootValido() {
        if (loot == null || loot.isBlank()) {
            return lootQnt == null;
        }

        return lootQnt != null && lootQnt >= 1;
    }
}
