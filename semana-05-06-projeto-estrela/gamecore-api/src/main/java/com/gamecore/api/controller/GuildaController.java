package com.gamecore.api.controller;

import com.gamecore.api.dto.GuildaRequestDTO;
import com.gamecore.api.dto.GuildaResponseDTO;
import com.gamecore.api.service.GuildaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guildas")
public class GuildaController {

    private final GuildaService guildaService;

    // Injeção de dependência do Service via construtor
    public GuildaController(GuildaService guildaService) {
        this.guildaService = guildaService;
    }

    // ENDPOINT 1: POST /guildas (Cadastrar nova guilda)
    @PostMapping
    public ResponseEntity<GuildaResponseDTO> criar(@RequestBody @Valid GuildaRequestDTO dto) {
        GuildaResponseDTO resposta = guildaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    // ENDPOINT 2: GET /guildas (Listar todas as guildas)
    @GetMapping
    public ResponseEntity<List<GuildaResponseDTO>> listarTodas() {
        List<GuildaResponseDTO> lista = guildaService.listarTodas();
        return ResponseEntity.ok(lista);
    }

    // ENDPOINT 3: GET /guildas/{id} (Buscar guilda por ID)
    @GetMapping("/{id}")
    public ResponseEntity<GuildaResponseDTO> buscarPorId(@PathVariable Long id) {
        GuildaResponseDTO guilda = guildaService.buscarPorId(id);
        return ResponseEntity.ok(guilda);
    }

    // ENDPOINT 4: PUT /guildas/{guildaId}/membros/{jogadorId} (Recrutar jogador
    // para a guilda)
    @PutMapping("/{guildaId}/membros/{jogadorId}")
    public ResponseEntity<Void> adicionarMembro(
            @PathVariable Long guildaId,
            @PathVariable Long jogadorId) {
        guildaService.adicionarMembro(guildaId, jogadorId);
        return ResponseEntity.noContent().build();
    }
}
