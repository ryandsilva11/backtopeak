package com.gamecore.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "jogadores")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    @Column(nullable = false, length = 30)
    private String classe;

    @Column(nullable = false)
    private Integer nivel = 1;

    @Column(name = "pontos_vida", nullable = false)
    private Integer pontosVida = 1000;

    @Column(name = "pontos_mana", nullable = false)
    private Integer pontosMana = 500;

    @Column(name = "moedas_ouro", nullable = false)
    private Double moedasOuro = 0.0;

    @ManyToOne
    @JoinColumn(name = "guilda_id")
    private Guilda guilda;

    public Jogador() {
    }

    public Jogador(Long id, String nome, String classe, Integer nivel, Integer pontosVida, Integer pontosMana,
            Double moedasOuro) {
        this.id = id;
        this.nome = nome;
        this.classe = classe;
        this.nivel = nivel;
        this.pontosVida = pontosVida;
        this.pontosMana = pontosMana;
        this.moedasOuro = moedasOuro;
    }

    // id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // classe
    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    // nivel
    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    // pontos_vida
    public Integer getPontosVida() {
        return pontosVida;
    }

    public void setPontosVida(Integer pontosVida) {
        this.pontosVida = pontosVida;
    }

    // pontos_mana
    public Integer getPontosMana() {
        return pontosMana;
    }

    public void setPontosMana(Integer pontosMana) {
        this.pontosMana = pontosMana;
    }

    // moedas_ouro
    public Double getMoedasOuro() {
        return moedasOuro;
    }

    public void setMoedasOuro(Double moedasOuro) {
        this.moedasOuro = moedasOuro;
    }

    // guilda
    public Guilda getGuilda() {
        return guilda;
    }

    public void setGuilda(Guilda guilda) {
        this.guilda = guilda;
    }

}
