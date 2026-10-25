package com.gamecore.api.repository;

import com.gamecore.api.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // 1. Para validar se já existe um item com o mesmo nome na loja
    boolean existsByNome(String nome);

    // 2. Para buscar por nome exato
    Optional<Item> findByNome(String nome);

    // 3. (Bônus para a Loja) Permite filtrar itens por categoria (ex: só "ARMA" ou
    // só "POCAO")
    List<Item> findByTipo(String tipo);

    List<Item> findByQuantidadeGreaterThan(Integer quantidade);

    List<Item> findByTipoAndQuantidadeGreaterThan(String tipo, Integer quantidade);

}
