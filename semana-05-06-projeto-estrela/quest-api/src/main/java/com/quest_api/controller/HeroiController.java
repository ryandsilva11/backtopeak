package com.quest_api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.quest_api.dto.HeroiRequestDTO;
import com.quest_api.dto.HeroiResponseDTO;
import com.quest_api.service.HeroiService;

@RestController
@RequestMapping("/herois")
public class HeroiController {

    private final HeroiService service;

    public HeroiController(HeroiService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HeroiResponseDTO> create(@RequestBody @Valid HeroiRequestDTO dto) {
        HeroiResponseDTO heroi = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(heroi);
    }

    @GetMapping
    ResponseEntity<List<HeroiResponseDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroiResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
