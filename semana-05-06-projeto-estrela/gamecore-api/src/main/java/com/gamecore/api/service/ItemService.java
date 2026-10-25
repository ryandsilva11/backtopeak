package com.gamecore.api.service;

import com.gamecore.api.dto.ItemRequestDTO;
import com.gamecore.api.dto.ItemResponseDTO;
import com.gamecore.api.model.Item;
import com.gamecore.api.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    // Injeção de dependência via construtor
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // 1. Cadastrar Item no catálogo
    @Transactional
    public ItemResponseDTO criar(ItemRequestDTO dto) {
        if (itemRepository.existsByNome(dto.nome())) {
            throw new RuntimeException("Já existe um item cadastrado com o nome: " + dto.nome());
        }

        Item novoItem = new Item(
                null,
                dto.nome(),
                dto.tipo(),
                dto.preco(),
                dto.poder(),
                dto.quantidade());

        Item itemSalvo = itemRepository.save(novoItem);
        return new ItemResponseDTO(itemSalvo);
    }

    // 2. Listar todo o catálogo da loja
    @Transactional(readOnly = true)
    public List<ItemResponseDTO> listarTodos() {
        return itemRepository.findByQuantidadeGreaterThan(0)
                .stream()
                .map(ItemResponseDTO::new)
                .toList();
    }

    // 3. Buscar item por ID
    @Transactional(readOnly = true)
    public ItemResponseDTO buscarPorId(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado com o ID: " + id));

        return new ItemResponseDTO(item);
    }

    // 4. Filtrar itens por tipo (ARMA, ARMADURA, POCAO, etc.)
    @Transactional(readOnly = true)
    public List<ItemResponseDTO> listarPorTipo(String tipo) {
        return itemRepository.findByTipoAndQuantidadeGreaterThan(tipo, 0)
                .stream()
                .map(ItemResponseDTO::new)
                .toList();
    }

    // 5. Deletar item do catálogo
    @Transactional
    public void deletar(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new RuntimeException("Item não encontrado com o ID: " + id);
        }
        itemRepository.deleteById(id);
    }
}
