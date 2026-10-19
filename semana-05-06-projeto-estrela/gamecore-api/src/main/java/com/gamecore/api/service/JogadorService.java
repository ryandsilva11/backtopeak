package com.gamecore.api.service;

import com.gamecore.api.dto.JogadorRequestDTO;
import com.gamecore.api.dto.JogadorResponseDTO;
import com.gamecore.api.model.Jogador;
import com.gamecore.api.repository.JogadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JogadorService {

    // 1. Dependência do Repository (declarada como final para segurança)
    private final JogadorRepository jogadorRepository;

    // 2. Injeção de Dependência via Construtor (o padrão moderno do Spring)
    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    // REGRA 1: Cadastrar um novo jogador
    @Transactional
    public JogadorResponseDTO criar(JogadorRequestDTO dto) {
        // Validação de regra de negócio: não aceitar nomes duplicados
        if (jogadorRepository.existsByNome(dto.nome())) {
            throw new RuntimeException("Já existe um jogador cadastrado com o nome: " + dto.nome());
        }

        // Instancia o jogador (que já nasce com nível 1, vida 1000, etc.)
        Jogador novoJogador = new Jogador(
                null, // ID nulo porque quem gera é o MySQL
                dto.nome(),
                dto.classe(),
                1, // nível inicial
                1000, // pontosVida
                500, // pontosMana
                0.0 // moedasOuro
        );

        // Salva no banco de dados através do Repository
        Jogador jogadorSalvo = jogadorRepository.save(novoJogador);

        // Devolve o ResponseDTO com o ID gerado pelo banco
        return new JogadorResponseDTO(jogadorSalvo);
    }

    // REGRA 2: Listar todos os jogadores
    @Transactional(readOnly = true)
    public List<JogadorResponseDTO> listarTodos() {
        return jogadorRepository.findAll()
                .stream()
                .map(JogadorResponseDTO::new)
                .toList();
    }

    // REGRA 3: Buscar jogador por ID
    @Transactional(readOnly = true)
    public JogadorResponseDTO buscarPorId(Long id) {
        Jogador jogador = jogadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado com o ID: " + id));

        return new JogadorResponseDTO(jogador);
    }

    // REGRA 4: Deletar jogador por ID
    @Transactional
    public void deletar(Long id) {
        if (!jogadorRepository.existsById(id)) {
            throw new RuntimeException("Jogador não encontrado com o ID: " + id);
        }
        jogadorRepository.deleteById(id);
    }
}
