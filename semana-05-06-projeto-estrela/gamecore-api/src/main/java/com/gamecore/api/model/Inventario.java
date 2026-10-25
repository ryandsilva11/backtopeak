package com.gamecore.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventarios")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Jogador (quem é o dono do item)
    @ManyToOne
    @JoinColumn(name = "jogador_id", nullable = false)
    private Jogador jogador;

    // Relacionamento com Item (qual item está guardado)
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Integer quantidade = 1;

    @Column(name = "data_aquisicao", nullable = false)
    private LocalDateTime dataAquisicao = LocalDateTime.now();

    // 1. Construtor padrão (obrigatório para o JPA)
    public Inventario() {
    }

    // 2. Construtor com argumentos
    public Inventario(Long id, Jogador jogador, Item item, Integer quantidade) {
        this.id = id;
        this.jogador = jogador;
        this.item = item;
        this.quantidade = quantidade;
        this.dataAquisicao = LocalDateTime.now();
    }

    // 3. Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDateTime dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }
}
