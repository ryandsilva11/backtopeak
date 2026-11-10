package com.quest_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.quest_api.dto.HeroiResponseDTO;
import com.quest_api.dto.QuestRequestDTO;
import com.quest_api.dto.QuestResponseDTO;
import com.quest_api.model.Quest;
import com.quest_api.model.Heroi;
import com.quest_api.repository.HeroiRepository;
import com.quest_api.repository.QuestRepository;

@Service
public class QuestService {

    private final QuestRepository qRepository;
    private final HeroiRepository hRepository;

    public QuestService(QuestRepository qRepository, HeroiRepository hRepository) {
        this.qRepository = qRepository;
        this.hRepository = hRepository;
    }

    @Transactional
    public QuestResponseDTO create(QuestRequestDTO dto) {
        if (qRepository.existsByTitulo(dto.titulo())) {
            throw new RuntimeException("Já existe uma quest cadastrada com esse titulo");
        }

        Quest quest = new Quest(
                null,
                dto.titulo(),
                dto.xp(),
                dto.loot(),
                dto.lootQnt(),
                false);

        Quest questSave = qRepository.save(quest);
        return new QuestResponseDTO(questSave);
    }

    @Transactional(readOnly = true)
    public List<QuestResponseDTO> listAll() {
        return qRepository.findAll().stream().map(QuestResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public QuestResponseDTO findById(Long id) {
        Quest quest = qRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quest não encontrada com o ID " + id));
        return new QuestResponseDTO(quest);
    }

    @Transactional
    public HeroiResponseDTO concluirQuest(Long hId, Long qId) {
        Quest quest = qRepository.findById(qId)
                .orElseThrow(() -> new RuntimeException("Quest não encontrada com o ID " + qId));

        if (quest.isConcluida()) {
            throw new RuntimeException("Essa quest já foi concluída");
        }

        Heroi heroi = hRepository.findById(hId)
                .orElseThrow(() -> new RuntimeException("Heroi não encontrado com o ID" + hId));

        heroi.setXp(heroi.getXp() + quest.getXp());
        if (heroi.getXp() >= heroi.getNivel() * 100) {
            heroi.setNivel(heroi.getNivel() + 1);
        }

        quest.setConcluida(true);
        qRepository.save(quest);

        Heroi heroiSalvo = hRepository.save(heroi);
        return new HeroiResponseDTO(heroiSalvo);
    }

}
