package com.gamecore.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gamecore.api.model.Jogador;

import java.util.List;
import java.util.Optional;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {

    boolean existsByNome(String nome);

    Optional<Jogador> findByNome(String nome);

    List<Jogador> findByGuildaId(Long guildaId);
}
