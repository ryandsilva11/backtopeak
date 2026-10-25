package com.gamecore.api.service;

import com.gamecore.api.dto.GuildaRequestDTO;
import com.gamecore.api.dto.GuildaResponseDTO;
import com.gamecore.api.model.Guilda;
import com.gamecore.api.model.Jogador;
import com.gamecore.api.repository.GuildaRepository;
import com.gamecore.api.repository.JogadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GuildaService {

    private final GuildaRepository guildaRepository;
    private final JogadorRepository jogadorRepository;

    // Injeção de dependência via construtor
    public GuildaService(GuildaRepository guildaRepository, JogadorRepository jogadorRepository) {
        this.guildaRepository = guildaRepository;
        this.jogadorRepository = jogadorRepository;
    }

    // 1. Cadastrar Guilda
    @Transactional
    public GuildaResponseDTO criar(GuildaRequestDTO dto) {
        if (guildaRepository.existsByNome(dto.nome())) {
            throw new RuntimeException("Já existe uma guilda cadastrada com o nome: " + dto.nome());
        }

        Guilda novaGuilda = new Guilda(
                null,
                dto.nome(),
                dto.lema());

        Guilda guildaSalva = guildaRepository.save(novaGuilda);
        return new GuildaResponseDTO(guildaSalva);
    }

    // 2. Listar todas as Guildas
    @Transactional(readOnly = true)
    public List<GuildaResponseDTO> listarTodas() {
        return guildaRepository.findAll()
                .stream()
                .map(GuildaResponseDTO::new)
                .toList();
    }

    // 3. Buscar Guilda por ID
    @Transactional(readOnly = true)
    public GuildaResponseDTO buscarPorId(Long id) {
        Guilda guilda = guildaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guilda não encontrada com o ID: " + id));

        return new GuildaResponseDTO(guilda);
    }

    // 4. Associar um Jogador a uma Guilda (Recrutamento)
    @Transactional
    public void adicionarMembro(Long guildaId, Long jogadorId) {
        Guilda guilda = guildaRepository.findById(guildaId)
                .orElseThrow(() -> new RuntimeException("Guilda não encontrada com o ID: " + guildaId));

        Jogador jogador = jogadorRepository.findById(jogadorId)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado com o ID: " + jogadorId));

        // Define a chave estrangeira (FK) na memória
        jogador.setGuilda(guilda);

        // Atualiza a linha do jogador no banco de dados (UPDATE jogadores SET guilda_id
        // = ...)
        jogadorRepository.save(jogador);
    }

    @Transactional
    public void deletar(Long id) {
        Guilda guilda = guildaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guilda não encontrada com o ID: " + id));
        // 1. Desvincula todos os jogadores da guilda (para não dar erro de FK no MySQL)
        List<Jogador> membros = jogadorRepository.findByGuildaId(id);
        for (Jogador membro : membros) {
            membro.setGuilda(null);
            jogadorRepository.save(membro);
        }
        // 2. Apaga a guilda com segurança
        guildaRepository.delete(guilda);
    }
}
