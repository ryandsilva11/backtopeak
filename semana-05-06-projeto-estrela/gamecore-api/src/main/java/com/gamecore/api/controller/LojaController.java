package com.gamecore.api.controller;

import com.gamecore.api.dto.CompraRequestDTO;
import com.gamecore.api.dto.InventarioResponseDTO;
import com.gamecore.api.service.LojaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loja")
public class LojaController {

    private final LojaService lojaService;

    // Injeção de dependência do Service via construtor
    public LojaController(LojaService lojaService) {
        this.lojaService = lojaService;
    }

    // ENDPOINT 1: POST /loja/comprar (Ação de Compra de Itens)
    @PostMapping("/comprar")
    public ResponseEntity<InventarioResponseDTO> comprar(
            @RequestBody @Valid CompraRequestDTO dto) {
        InventarioResponseDTO resposta = lojaService.comprarItem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    // ENDPOINT 2: GET /loja/inventario/{jogadorId} (Ver o inventário de um jogador)
    @GetMapping("/inventario/{jogadorId}")
    public ResponseEntity<List<InventarioResponseDTO>> verInventario(
            @PathVariable Long jogadorId) {
        List<InventarioResponseDTO> inventario = lojaService.listarInventarioPorJogador(jogadorId);
        return ResponseEntity.ok(inventario);
    }
}
