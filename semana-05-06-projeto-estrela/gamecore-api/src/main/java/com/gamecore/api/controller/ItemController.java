package com.gamecore.api.controller;

import com.gamecore.api.dto.ItemRequestDTO;
import com.gamecore.api.dto.ItemResponseDTO;
import com.gamecore.api.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    private final ItemService itemService;

    // Injeção de dependência via construtor
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // ENDPOINT 1: POST /itens (Cadastrar novo item no catálogo)
    @PostMapping
    public ResponseEntity<ItemResponseDTO> criar(@RequestBody @Valid ItemRequestDTO dto) {
        ItemResponseDTO resposta = itemService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    // ENDPOINT 2: GET /itens (Listar todos ou filtrar por tipo via ?tipo=ARMA)
    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> listar(
            @RequestParam(required = false) String tipo) {

        List<ItemResponseDTO> itens;
        if (tipo != null && !tipo.isBlank()) {
            itens = itemService.listarPorTipo(tipo);
        } else {
            itens = itemService.listarTodos();
        }

        return ResponseEntity.ok(itens);
    }

    // ENDPOINT 3: GET /itens/{id} (Buscar item por ID)
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDTO> buscarPorId(@PathVariable Long id) {
        ItemResponseDTO item = itemService.buscarPorId(id);
        return ResponseEntity.ok(item);
    }

    // ENDPOINT 4: DELETE /itens/{id} (Remover item do catálogo)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itemService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
