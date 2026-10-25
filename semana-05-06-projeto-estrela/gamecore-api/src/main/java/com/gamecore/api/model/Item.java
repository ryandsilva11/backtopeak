package com.gamecore.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "itens")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    @Column(nullable = false, length = 30)
    private String tipo;

    @Column(nullable = false)
    private Double preco;

    @Column
    private Integer poder;

    // 1. Construtor padrão (obrigatório para o JPA)
    public Item() {
    }

    // 2. Construtor com argumentos
    public Item(Long id, String nome, String tipo, Double preco, Integer poder) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
        this.poder = poder;
    }

    // 3. Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getPoder() {
        return poder;
    }

    public void setPoder(Integer poder) {
        this.poder = poder;
    }
}
