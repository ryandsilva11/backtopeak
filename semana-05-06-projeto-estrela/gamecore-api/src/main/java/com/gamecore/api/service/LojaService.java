package com.gamecore.api.service;

import com.gamecore.api.dto.CompraRequestDTO;
import com.gamecore.api.dto.InventarioResponseDTO;
import com.gamecore.api.model.Inventario;
import com.gamecore.api.model.Item;
import com.gamecore.api.model.Jogador;
import com.gamecore.api.repository.InventarioRepository;
import com.gamecore.api.repository.ItemRepository;
import com.gamecore.api.repository.JogadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LojaService {

    private final JogadorRepository jogadorRepository;
    private final ItemRepository itemRepository;
    private final InventarioRepository inventarioRepository;

    // Injeção de dependência dos 3 repositories via construtor
    public LojaService(JogadorRepository jogadorRepository,
            ItemRepository itemRepository,
            InventarioRepository inventarioRepository) {
        this.jogadorRepository = jogadorRepository;
        this.itemRepository = itemRepository;
        this.inventarioRepository = inventarioRepository;
    }

    // 1. REGRA PRINCIPAL: Ação de Compra de Itens na Loja
    @Transactional
    public InventarioResponseDTO comprarItem(CompraRequestDTO dto) {
        // 1. Busca o comprador
        Jogador jogador = jogadorRepository.findById(dto.jogadorId())
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado com o ID: " + dto.jogadorId()));

        // 2. Busca o item na vitrine
        Item item = itemRepository.findById(dto.itemId())
                .orElseThrow(() -> new RuntimeException("Item não encontrado com o ID: " + dto.itemId()));

        // 3. NOVO: Validação de Estoque na Loja
        if (item.getQuantidade() < dto.quantidade()) {
            throw new RuntimeException("Estoque insuficiente na loja! Disponível: "
                    + item.getQuantidade() + ", Solicitado: " + dto.quantidade());
        }

        // 4. Calcula o valor total da compra
        double custoTotal = item.getPreco() * dto.quantidade();

        // 5. Validação de regra de negócio: Saldo Suficiente
        if (jogador.getMoedasOuro() < custoTotal) {
            throw new RuntimeException("Saldo insuficiente! Ouro atual: "
                    + jogador.getMoedasOuro() + ", Custo total da compra: " + custoTotal);
        }

        // 6. Debita as moedas de ouro do jogador
        jogador.setMoedasOuro(jogador.getMoedasOuro() - custoTotal);
        jogadorRepository.save(jogador);

        // 7. NOVO: Abate a quantidade comprada do estoque da loja
        item.setQuantidade(item.getQuantidade() - dto.quantidade());
        itemRepository.save(item);

        // 8. Atualiza a mochila do jogador (empilha ou cria novo slot)
        Optional<Inventario> slotExistente = inventarioRepository.findByJogadorIdAndItemId(dto.jogadorId(),
                dto.itemId());

        Inventario inventarioSalvo;
        if (slotExistente.isPresent()) {
            Inventario slot = slotExistente.get();
            slot.setQuantidade(slot.getQuantidade() + dto.quantidade());
            inventarioSalvo = inventarioRepository.save(slot);
        } else {
            Inventario novoSlot = new Inventario(null, jogador, item, dto.quantidade());
            inventarioSalvo = inventarioRepository.save(novoSlot);
        }

        return new InventarioResponseDTO(inventarioSalvo);
    }

    @Transactional(readOnly = true)
    public List<InventarioResponseDTO> listarInventarioPorJogador(Long jogadorId) {
        if (!jogadorRepository.existsById(jogadorId)) {
            throw new RuntimeException("Jogador não encontrado com o ID: " + jogadorId);
        }

        return inventarioRepository.findByJogadorId(jogadorId)
                .stream()
                .map(InventarioResponseDTO::new)
                .toList();
    }
}
