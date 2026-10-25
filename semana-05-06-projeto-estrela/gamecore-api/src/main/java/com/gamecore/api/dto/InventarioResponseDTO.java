package com.gamecore.api.dto;

import com.gamecore.api.model.Inventario;
import java.time.LocalDateTime;

public record InventarioResponseDTO(
        Long id,
        Long itemId,
        String nomeItem,
        String tipoItem,
        Integer poderItem,
        Integer quantidade,
        LocalDateTime dataAquisicao) {
    public InventarioResponseDTO(Inventario inventario) {
        this(
                inventario.getId(),
                inventario.getItem().getId(),
                inventario.getItem().getNome(),
                inventario.getItem().getTipo(),
                inventario.getItem().getPoder(),
                inventario.getQuantidade(),
                inventario.getDataAquisicao());
    }
}
