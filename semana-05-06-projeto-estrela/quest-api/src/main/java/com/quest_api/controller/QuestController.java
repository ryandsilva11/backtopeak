package com.quest_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.quest_api.dto.HeroiResponseDTO;
import com.quest_api.dto.QuestRequestDTO;
import com.quest_api.dto.QuestResponseDTO;
import com.quest_api.service.QuestService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/quests")
public class QuestController {

    private final QuestService service;

    public QuestController(QuestService service) {
        this.service = service;
    }

    @PatchMapping("/{qId}/concluir/{hId}")
    public ResponseEntity<HeroiResponseDTO> concluir(@PathVariable Long qId, @PathVariable Long hId) {
        HeroiResponseDTO heroiUpdt = service.concluirQuest(hId, qId);
        return ResponseEntity.ok(heroiUpdt);
    }

    @PostMapping
    public ResponseEntity<QuestResponseDTO> create(@RequestBody @Valid QuestRequestDTO dto) {

        QuestResponseDTO quest = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(quest);
    }

    @GetMapping
    public ResponseEntity<List<QuestResponseDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}