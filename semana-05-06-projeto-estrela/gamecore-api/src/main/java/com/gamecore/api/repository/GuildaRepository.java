package com.gamecore.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gamecore.api.model.Guilda;
import java.util.Optional;

public interface GuildaRepository extends JpaRepository<Guilda, Long> {

    boolean existsByNome(String nome);

    Optional<Guilda> findByNome(String nome);
}
