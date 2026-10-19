package com.gamecore.api.controller;

import com.gamecore.api.dto.JogadorRequestDTO;
import com.gamecore.api.dto.JogadorResponseDTO;
import com.gamecore.api.service.JogadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    private final JogadorService jogadorService;

    // Injeção de dependência do Service via construtor
    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    // ENDPOINT 1: POST /jogadores (Cadastrar novo jogador)
    @PostMapping
    public ResponseEntity<JogadorResponseDTO> criar(@RequestBody @Valid JogadorRequestDTO dto) {
        JogadorResponseDTO resposta = jogadorService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    // ENDPOINT 2: GET /jogadores (Listar todos os jogadores)
    @GetMapping
    public ResponseEntity<List<JogadorResponseDTO>> listarTodos() {
        List<JogadorResponseDTO> lista = jogadorService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    // ENDPOINT 3: GET /jogadores/{id} (Buscar jogador por ID)
    @GetMapping("/{id}")
    public ResponseEntity<JogadorResponseDTO> buscarPorId(@PathVariable Long id) {
        JogadorResponseDTO jogador = jogadorService.buscarPorId(id);
        return ResponseEntity.ok(jogador);
    }

    // ENDPOINT 4: DELETE /jogadores/{id} (Deletar jogador por ID)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        jogadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
