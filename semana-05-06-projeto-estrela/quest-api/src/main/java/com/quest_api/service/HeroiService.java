package com.quest_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.quest_api.dto.HeroiRequestDTO;
import com.quest_api.dto.HeroiResponseDTO;
import com.quest_api.model.Heroi;
import com.quest_api.repository.HeroiRepository;

@Service
public class HeroiService {

    private final HeroiRepository repository;

    public HeroiService(HeroiRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public HeroiResponseDTO create(HeroiRequestDTO dto) {
        if (repository.existsByNome(dto.nome())) {
            throw new RuntimeException("...");
        }

        Heroi heroi = new Heroi(
                null,
                dto.nome(),
                dto.classe(),
                1L,
                0L);

        Heroi heroiSave = repository.save(heroi);
        return new HeroiResponseDTO(heroiSave);
    }

    @Transactional(readOnly = true)
    public List<HeroiResponseDTO> listAll() {
        return repository.findAll().stream().map(HeroiResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public HeroiResponseDTO findById(Long id) {
        Heroi heroi = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Heroi não encontrado com o id " + id));
        return new HeroiResponseDTO(heroi);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Herói não encontrado com o ID " + id);
        }
        repository.deleteById(id);
    }
}