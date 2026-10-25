package com.gamecore.api.repository;

import com.gamecore.api.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    // 1. Busca todos os slots de inventário de um jogador
    List<Inventario> findByJogadorId(Long jogadorId);

    // 2. Busca se o jogador já possui um item específico na mochila (para empilhar
    // quantidade)
    Optional<Inventario> findByJogadorIdAndItemId(Long jogadorId, Long itemId);
}
