package com.gamecore.api.dto;

import com.gamecore.api.model.Item;

public record ItemResponseDTO(
        Long id,
        String nome,
        String tipo,
        Double preco,
        Integer poder,
        Integer quantidade) {
    public ItemResponseDTO(Item item) {
        this(
                item.getId(),
                item.getNome(),
                item.getTipo(),
                item.getPreco(),
                item.getPoder(),
                item.getQuantidade());
    }
}
